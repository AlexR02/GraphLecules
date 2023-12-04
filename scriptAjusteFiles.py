import os
import re
import csv
import shutil
import sys

diretory = sys.argv[1]
#diretory = '/home/alex/CHEMBL'

# Listar todos os arquivos no diretório
files = os.listdir(diretory)

checkHeader = ["molecule_chembl_id", "pref_name", "synonyms", "molecule_type", "max_phase", "full_mwt", "alogp", "psa", "hba", "hbd", "num_ro5_violations", "rtb", "ro3_pass", "qed_weighted", "cx_most_apka", "cx_most_bpka", "cx_logp", "cx_logd", "aromatic_rings", "structure_type", "inorganic_flag", "heavy_atoms", "hba_lipinski", "hbd_lipinski", "num_lipinski_ro5_violations", "mw_monoisotopic", "np_likeness_score", "molecular_species", "full_molformula", "canonical_smiles", "standard_inchi_key"]
outFileName = "graphLeculesFile.csv"
outFile = open(outFileName, "w")
outFile.write(",".join(checkHeader) + "\n")

count = 0
# Iterar sobre os arquivos
for file in files:
    file = open(os.path.join(diretory, file))
    fileCSV = csv.reader(file)
    
    lines = [line for line in fileCSV]
    firstLineSplited = lines[0]
    secondLineSplited = lines[1]
    secondLine = ",".join(lines[1])
    dictProperties = {key: '"None"' for key in checkHeader}

    idx = 0
    for propertie in firstLineSplited:
        if propertie in checkHeader:
            dictProperties[propertie] = '"' + secondLineSplited[idx] + '"'
        idx += 1
        
    for key, value in dictProperties.items():

        match = None

        if value == '"None"' and key != "synonyms":
            match = re.search(r"'" + key + "': '{0,1}([^'}{,]+)'{0,1}", secondLine)
        elif value == '"None"':
            match = re.search(r"'synonyms': \"([^\"]+)\"", secondLine)

        if match:
            newValue = match.group(1)
            dictProperties[key] = '"' + newValue + '"'
            
    outFile.write(",".join(dictProperties.values()) + "\n")

    count += 1
    if count >= int(sys.argv[2]):
        break

outFile.close()
