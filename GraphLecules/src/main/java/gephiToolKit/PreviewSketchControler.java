package gephiToolKit;

import gephiFileProcessor.GephiFileProcessor;
import gephiFileProcessor.InformationalFileProcessor;
import gephiFileProcessor.NetworkInformationalControler;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.ImportController;
import org.gephi.io.processor.plugin.DefaultProcessor;
import org.gephi.layout.plugin.AutoLayout;
import org.gephi.layout.plugin.force.StepDisplacement;
import org.gephi.layout.plugin.force.yifanHu.YifanHuLayout;
import org.gephi.layout.plugin.forceAtlas.ForceAtlasLayout;
import org.gephi.layout.plugin.fruchterman.FruchtermanReingold;
import org.gephi.preview.api.G2DTarget;
import org.gephi.preview.api.PreviewController;
import org.gephi.preview.api.PreviewModel;
import org.gephi.preview.api.PreviewProperty;
import org.gephi.preview.api.RenderTarget;
import org.gephi.preview.types.DependantOriginalColor;
import org.gephi.preview.types.EdgeColor;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author alex
 */
public class PreviewSketchControler {

    //private static List<Edge> removedEdges = new ArrayList<>();
    private Workspace initProject(String filePath, String informationalFilePath) {
        //Init a project - and therefore a workspace
        ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
        pc.newProject();
        Workspace workspace = pc.getCurrentWorkspace();

        //Import file
        ImportController importController = Lookup.getDefault().lookup(ImportController.class);
        Container container;
        try {
            File file = new File(filePath);
            String[] parse = filePath.split("[.]");
            String extencion = parse[parse.length - 1];

            if (extencion.equals("csv")) {
                file = GephiFileProcessor.processGephiFile(file, null);
            }

            container = importController.importFile(file);
        } catch (FileNotFoundException ex) {
            return null;
        }

        //Append imported data to GraphAPI
        importController.process(container, new DefaultProcessor(), workspace);

        if (NetworkInformationalControler.getMoleculeByIDMap() == null || NetworkInformationalControler.getMoleculeByIDMap().isEmpty()) {
            GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel(workspace);
            InformationalFileProcessor.processInformationalFile(new File(informationalFilePath), graphModel.getGraph());
        }

        return workspace;
    }

    private void applyLayout(int idxLayoutSelected, Long layoutTime) {
        GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();

        //Layout for 1 minute
        AutoLayout autoLayout = new AutoLayout(layoutTime, TimeUnit.SECONDS);
        autoLayout.setGraphModel(graphModel);

        switch (idxLayoutSelected) {
            case 0:
                FruchtermanReingold fruchtermanReingold = new FruchtermanReingold(null);
                autoLayout.addLayout(fruchtermanReingold, 1f);
                break;

            case 1:
                ForceAtlasLayout forceAtlas = new ForceAtlasLayout(null);
                autoLayout.addLayout(forceAtlas, 1f);
                break;

            case 2:
                YifanHuLayout yifanHuLayout = new YifanHuLayout(null, new StepDisplacement(1f));
                autoLayout.addLayout(yifanHuLayout, 1f);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Could not aplicate any Layout", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }

        autoLayout.execute();

    }

    private PreviewController generatePreviewControler() {

        PreviewController previewController = Lookup.getDefault().lookup(PreviewController.class);
        PreviewModel previewModel = previewController.getModel();

        previewModel.getProperties().putValue(PreviewProperty.NODE_LABEL_COLOR, new DependantOriginalColor(Color.WHITE));
        previewModel.getProperties().putValue(PreviewProperty.NODE_LABEL_PROPORTIONAL_SIZE, Boolean.TRUE);
        previewModel.getProperties().putValue(PreviewProperty.EDGE_CURVED, Boolean.TRUE);
        previewModel.getProperties().putValue(PreviewProperty.EDGE_COLOR, new EdgeColor(Color.GRAY));
        previewModel.getProperties().putValue(PreviewProperty.BACKGROUND_COLOR, Color.WHITE);
        previewController.refreshPreview();

        return previewController;
    }

    private void paintNetworkNodes(Workspace workspace) {
        GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel(workspace);

        Graph graph = graphModel.getGraph();

        long maxDegree = 0;
        for (Node node : graph.getNodes().toArray()) {
            int degree = graph.getDegree(node);
            if (degree > maxDegree) {
                maxDegree = degree;
            }
        }

        for (Node node : graph.getNodes().toArray()) {
            int degree = graph.getDegree(node);

            double normalizedDegree = (double) degree / maxDegree;

            Color color = gradientColor(normalizedDegree);

            node.setColor(color);
        }
    }

    private Color gradientColor(double value) {
        int r = (int) (255 * value);
        int b = (int) (255 * (1 - value));
        return new Color(r, 0, b);
    }

    public void cutEdges(Float threshold, Workspace workspace) {

        GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel(workspace);

        Graph graph = graphModel.getGraph();

        for (Edge edge : graph.getEdges().toArray()) {
            if (edge.getWeight() < threshold.doubleValue()) {
                graph.removeEdge(edge);
            }
        }

    }

    public PreviewSketch drawSketch(int idxLayoutSelected, Long layoutTime, Float threshold, String filePath, String informationalFilePath) {

        Workspace workspace = initProject(filePath, informationalFilePath);

        cutEdges(threshold, workspace);

        applyLayout(idxLayoutSelected, layoutTime);

        PreviewController previewController = generatePreviewControler();

        paintNetworkNodes(workspace);

        previewController.refreshPreview();

        G2DTarget target = (G2DTarget) previewController.getRenderTarget(RenderTarget.G2D_TARGET);

        return new PreviewSketch(target);
    }
}
