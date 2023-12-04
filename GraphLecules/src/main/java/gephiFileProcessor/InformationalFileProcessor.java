/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gephiFileProcessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.gephi.graph.api.Graph;

/**
 *
 * @author alex
 */
public class InformationalFileProcessor {

    public static void processInformationalFile(File file, Graph graph) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;

            bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null) {

                line = line.replace("\"", "");
                String[] parsedLine = line.split(",");

                if (graph.getNode(parsedLine[29]) != null) {
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
                }
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            return;
        }
    }
}
