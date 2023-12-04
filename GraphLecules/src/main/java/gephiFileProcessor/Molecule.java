/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gephiFileProcessor;

/**
 *
 * @author alex
 */
public class Molecule {
    private String moleculeChemblId;
    private String prefName;
    private String synonyms;
    private String moleculeType;
    private String maxPhase;
    private String fullMwt;
    private String alogp;
    private String psa;
    private String hba;
    private String hbd;
    private String numRo5Violations;
    private String rtb;
    private String ro3Pass;
    private String qedWeighted;
    private String cxMostApka;
    private String cxMostBpka;
    private String cxLogp;
    private String cxLogd;
    private String aromaticRings;
    private String structureType;
    private String inorganicFlag;
    private String heavyAtoms;
    private String hbaLipinski;
    private String hbdLipinski;
    private String numLipinskiRo5Violations;
    private String mwMonoisotopic;
    private String npLikenessScore;
    private String molecularSpecies;
    private String fullMolformula;
    private String canonicalSmiles;
    private String standardInchiKey;
    private String imagePath;

    // Construtor
    public Molecule() {
        // Construtor padrão
    }

    public String getMoleculeChemblId() {
        return moleculeChemblId;
    }

    public void setMoleculeChemblId(String moleculeChemblId) {
        this.moleculeChemblId = moleculeChemblId;
    }

    public String getPrefName() {
        return prefName;
    }

    public void setPrefName(String prefName) {
        this.prefName = prefName;
    }

    public String getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
    }

    public String getMoleculeType() {
        return moleculeType;
    }

    public void setMoleculeType(String moleculeType) {
        this.moleculeType = moleculeType;
    }

    public String getMaxPhase() {
        return maxPhase;
    }

    public void setMaxPhase(String maxPhase) {
        this.maxPhase = maxPhase;
    }

    public String getFullMwt() {
        return fullMwt;
    }

    public void setFullMwt(String fullMwt) {
        this.fullMwt = fullMwt;
    }

    public String getAlogp() {
        return alogp;
    }

    public void setAlogp(String alogp) {
        this.alogp = alogp;
    }

    public String getPsa() {
        return psa;
    }

    public void setPsa(String psa) {
        this.psa = psa;
    }

    public String getHba() {
        return hba;
    }

    public void setHba(String hba) {
        this.hba = hba;
    }

    public String getHbd() {
        return hbd;
    }

    public void setHbd(String hbd) {
        this.hbd = hbd;
    }

    public String getNumRo5Violations() {
        return numRo5Violations;
    }

    public void setNumRo5Violations(String numRo5Violations) {
        this.numRo5Violations = numRo5Violations;
    }

    public String getRtb() {
        return rtb;
    }

    public void setRtb(String rtb) {
        this.rtb = rtb;
    }

    public String getRo3Pass() {
        return ro3Pass;
    }

    public void setRo3Pass(String ro3Pass) {
        this.ro3Pass = ro3Pass;
    }

    public String getQedWeighted() {
        return qedWeighted;
    }

    public void setQedWeighted(String qedWeighted) {
        this.qedWeighted = qedWeighted;
    }

    public String getCxMostApka() {
        return cxMostApka;
    }

    public void setCxMostApka(String cxMostApka) {
        this.cxMostApka = cxMostApka;
    }

    public String getCxMostBpka() {
        return cxMostBpka;
    }

    public void setCxMostBpka(String cxMostBpka) {
        this.cxMostBpka = cxMostBpka;
    }

    public String getCxLogp() {
        return cxLogp;
    }

    public void setCxLogp(String cxLogp) {
        this.cxLogp = cxLogp;
    }

    public String getCxLogd() {
        return cxLogd;
    }

    public void setCxLogd(String cxLogd) {
        this.cxLogd = cxLogd;
    }

    public String getAromaticRings() {
        return aromaticRings;
    }

    public void setAromaticRings(String aromaticRings) {
        this.aromaticRings = aromaticRings;
    }

    public String getStructureType() {
        return structureType;
    }

    public void setStructureType(String structureType) {
        this.structureType = structureType;
    }

    public String getInorganicFlag() {
        return inorganicFlag;
    }

    public void setInorganicFlag(String inorganicFlag) {
        this.inorganicFlag = inorganicFlag;
    }

    public String getHeavyAtoms() {
        return heavyAtoms;
    }

    public void setHeavyAtoms(String heavyAtoms) {
        this.heavyAtoms = heavyAtoms;
    }

    public String getHbaLipinski() {
        return hbaLipinski;
    }

    public void setHbaLipinski(String hbaLipinski) {
        this.hbaLipinski = hbaLipinski;
    }

    public String getHbdLipinski() {
        return hbdLipinski;
    }

    public void setHbdLipinski(String hbdLipinski) {
        this.hbdLipinski = hbdLipinski;
    }

    public String getNumLipinskiRo5Violations() {
        return numLipinskiRo5Violations;
    }

    public void setNumLipinskiRo5Violations(String numLipinskiRo5Violations) {
        this.numLipinskiRo5Violations = numLipinskiRo5Violations;
    }

    public String getMwMonoisotopic() {
        return mwMonoisotopic;
    }

    public void setMwMonoisotopic(String mwMonoisotopic) {
        this.mwMonoisotopic = mwMonoisotopic;
    }

    public String getNpLikenessScore() {
        return npLikenessScore;
    }

    public void setNpLikenessScore(String npLikenessScore) {
        this.npLikenessScore = npLikenessScore;
    }

    public String getMolecularSpecies() {
        return molecularSpecies;
    }

    public void setMolecularSpecies(String molecularSpecies) {
        this.molecularSpecies = molecularSpecies;
    }

    public String getFullMolformula() {
        return fullMolformula;
    }

    public void setFullMolformula(String fullMolformula) {
        this.fullMolformula = fullMolformula;
    }

    public String getCanonicalSmiles() {
        return canonicalSmiles;
    }

    public void setCanonicalSmiles(String canonicalSmiles) {
        this.canonicalSmiles = canonicalSmiles;
    }

    public String getStandardInchiKey() {
        return standardInchiKey;
    }

    public void setStandardInchiKey(String standardInchiKey) {
        this.standardInchiKey = standardInchiKey;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
}
