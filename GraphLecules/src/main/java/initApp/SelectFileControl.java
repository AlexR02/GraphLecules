/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package initApp;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author alex
 */
public class SelectFileControl {

    private String newProjectPath;
    private String openProjectPath;
    private String informationalFilePath;

    public String getNewProjectPath() {
        return newProjectPath;
    }

    public void setNewProjectPath(String newProjectPath) {
        this.newProjectPath = newProjectPath;
    }

    public String getOpenProjectPath() {
        return openProjectPath;
    }

    public void setOpenProjectPath(String openProjectPath) {
        this.openProjectPath = openProjectPath;
    }

    public String getInformationalFilePath() {
        return informationalFilePath;
    }

    public void setInformationalFilePath(String informationalFilePath) {
        this.informationalFilePath = informationalFilePath;
    }

    public void openProject(JFrame father, String currentPath) {
        JFileChooser jFileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Gephi files (.gexf)", "gexf");
        jFileChooser.setFileFilter(filter);
        jFileChooser.setCurrentDirectory(new File(currentPath));

        int escolha = jFileChooser.showOpenDialog(father);

        if (escolha == JFileChooser.APPROVE_OPTION) {
            // O usuário escolheu um arquivo
            // Lógica para lidar com o arquivo selecionado
            String[] parse = jFileChooser.getSelectedFile().getAbsolutePath().split("[.]");
            String extensão = parse[parse.length - 1];
            if (!extensão.equals("gexf")) {
                JOptionPane.showMessageDialog(father, "This type of file is not accepted.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            this.openProjectPath = jFileChooser.getSelectedFile().getAbsolutePath();
            // Faça algo com o caminhoDoArquivo...
        }
    }

    public void informationalFile(JFrame father, String currentPath) {
        JFileChooser jFileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("csv File (*.csv)", "csv");
        jFileChooser.setFileFilter(filter);
        jFileChooser.setCurrentDirectory(new File(currentPath));

        int escolha = jFileChooser.showOpenDialog(father);

        if (escolha == JFileChooser.APPROVE_OPTION) {
            // O usuário escolheu um arquivo
            // Lógica para lidar com o arquivo selecionado
            String[] parse = jFileChooser.getSelectedFile().getAbsolutePath().split("[.]");
            String extensão = parse[parse.length - 1];
            if (!extensão.equals("csv")) {
                JOptionPane.showMessageDialog(father, "This type of file is not accepted.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            this.informationalFilePath = jFileChooser.getSelectedFile().getAbsolutePath();
            // Faça algo com o caminhoDoArquivo...
        }
    }
    public void newProject(JFrame father, String currentPath) {
        JFileChooser jFileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("csv File (*.csv)", "csv");
        jFileChooser.setFileFilter(filter);
        jFileChooser.setCurrentDirectory(new File(currentPath));

        int escolha = jFileChooser.showOpenDialog(father);

        if (escolha == JFileChooser.APPROVE_OPTION) {
            // O usuário escolheu um arquivo
            // Lógica para lidar com o arquivo selecionado
            String[] parse = jFileChooser.getSelectedFile().getAbsolutePath().split("[.]");
            String extensão = parse[parse.length - 1];
            if (!extensão.equals("csv")) {
                JOptionPane.showMessageDialog(father, "This type of file is not accepted.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            this.newProjectPath = jFileChooser.getSelectedFile().getAbsolutePath();
            // Faça algo com o caminhoDoArquivo...
        }
    }

}
