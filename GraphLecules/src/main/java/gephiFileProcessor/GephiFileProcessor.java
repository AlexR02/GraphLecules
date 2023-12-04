/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gephiFileProcessor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author alex
 */
public class GephiFileProcessor {

    public static File processGephiFile(File csvFile, JFrame parent) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String newFileName = "project_" + sdf.format(Calendar.getInstance().getTime()) + ".gexf";

        String userHome = System.getProperty("user.home");

        File folder = new File(userHome + File.separator + "GraphLeculesOutFiles");

        if (!folder.exists() && !folder.mkdir()) {
            JOptionPane.showMessageDialog(parent, "Could not create the project folder in the user directory.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        File returnFile = new File(folder.getAbsolutePath() + File.separator + newFileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(returnFile))) {
            NetworkInformationalControler.getMoleculeByIDMap().clear();
            writer.write(generateGephiFileContent(csvFile));
            return returnFile;
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    private static String generateGephiFileContent(File csvFile) {

        StringBuilder fileContent = new StringBuilder();

        String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<gexf xmlns:viz=\"http:///www.gexf.net/1.1draft/viz\" version=\"1.1\"\n"
                + "    xmlns=\"http://www.gexf.net/1.1draft\">\n"
                + "    <meta lastmodifieddate=\"2010-04-01+00:34\">\n"
                + "        <creator>GraphLecules 0.1 - Alex</creator>\n"
                + "    </meta>\n"
                + "    <graph defaultedgetype=\"undirected\" idtype=\"string\" type=\"static\">\n";

        String footer = "    </graph>\n"
                + "</gexf>";

        fileContent.append(header);

        StringBuffer nodeStringBuffer = new StringBuffer();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            Long countNode = 1l;

            bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null) {

                String[] parsedLine = line.split("\",\"");
                for (int i = 0; i < parsedLine.length; i++) {
                    parsedLine[i] = parsedLine[i].replace("\"", "");
                }

                Molecule molecule = new Molecule();
                molecule.setMoleculeChemblId(parsedLine[0]);
                molecule.setPrefName(parsedLine[1]);
                molecule.setSynonyms(parsedLine[2]);
                molecule.setMoleculeType(parsedLine[3]);
                molecule.setMaxPhase(parsedLine[4]);
                molecule.setFullMwt(parsedLine[5]);
                molecule.setAlogp(parsedLine[6]);
                molecule.setPsa(parsedLine[7]);
                molecule.setHba(parsedLine[8]);
                molecule.setHbd(parsedLine[9]);
                molecule.setNumRo5Violations(parsedLine[10]);
                molecule.setRtb(parsedLine[11]);
                molecule.setRo3Pass(parsedLine[12]);
                molecule.setQedWeighted(parsedLine[13]);
                molecule.setCxMostApka(parsedLine[14]);
                molecule.setCxMostBpka(parsedLine[15]);
                molecule.setCxLogp(parsedLine[16]);
                molecule.setCxLogd(parsedLine[17]);
                molecule.setAromaticRings(parsedLine[18]);
                molecule.setStructureType(parsedLine[19]);
                molecule.setInorganicFlag(parsedLine[20]);
                molecule.setHeavyAtoms(parsedLine[21]);
                molecule.setHbaLipinski(parsedLine[22]);
                molecule.setHbdLipinski(parsedLine[23]);
                molecule.setNumLipinskiRo5Violations(parsedLine[24]);
                molecule.setMwMonoisotopic(parsedLine[25]);
                molecule.setNpLikenessScore(parsedLine[26]);
                molecule.setMolecularSpecies(parsedLine[27]);
                molecule.setFullMolformula(parsedLine[28]);
                molecule.setCanonicalSmiles(parsedLine[29]);
                molecule.setStandardInchiKey(parsedLine[30]);

                NetworkInformationalControler.getMoleculeByIDMap().put(molecule.getCanonicalSmiles(), molecule);

                nodeStringBuffer.append("            <node id=\"").append(molecule.getCanonicalSmiles()).append("\" label=\"").append(molecule.getMoleculeChemblId()).append("\">\n                <viz:size value=\"8\" />\n            </node>\n");

                countNode += 1;
            }

            String nodesTagOpen = "        <nodes count=\"" + (countNode - 1) + "\">\n";
            String nodesTagClose = "        </nodes>\n";

            fileContent.append(nodesTagOpen).append(nodeStringBuffer).append(nodesTagClose);

            ProcessBuilder processBuilder = new ProcessBuilder("python3", GephiFileProcessor.class.getResource("/pythonScripts/scriptTanimotoGraphLecules.py").toString().replace("file:", ""), csvFile.getAbsolutePath());
            Process process = processBuilder.start();

            BufferedReader bufferedReaderPython = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String edge;
            while ((edge = bufferedReaderPython.readLine()) != null) {
                fileContent.append(edge).append("\n");
            }
            
            bufferedReaderPython = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String error;
            while((error = bufferedReaderPython.readLine()) != null){
                System.err.println(error);
            }
            
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            return "";
        }

        fileContent.append(footer);

        return fileContent.toString();
    }

}
