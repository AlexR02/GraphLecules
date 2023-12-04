/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gephiFileProcessor;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author alex
 */
public class NetworkInformationalControler {

    private static Map<String, Molecule> moleculeByIDMap = new HashMap<>();

    public static Map<String, Molecule> getMoleculeByIDMap() {
        return moleculeByIDMap;
    }
    
}
