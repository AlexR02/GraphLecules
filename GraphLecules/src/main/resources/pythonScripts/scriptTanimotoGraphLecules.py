# RDKit stuff
from rdkit import Chem
from rdkit import rdBase
from rdkit.Chem import Draw
from rdkit.Chem.Draw import rdMolDraw2D
from rdkit.Chem import rdDepictor
rdDepictor.SetPreferCoordGen(True)
from rdkit.Chem import rdMolDescriptors
from rdkit.Chem import rdFMCS
from rdkit import DataStructs
from rdkit.Chem import rdmolops

# numpy
import numpy as np

# pandas
import pandas as pd

# matplotlib
import matplotlib as mpl
import matplotlib.pyplot as plt

import multiprocessing
#print("multiprocessing cpu count:", multiprocessing.cpu_count())

# From the Python docs, this below is number of usable CPUs (works on Unix/Linux)
# https://docs.python.org/3/library/multiprocessing.html
# we subtracted 2 from total number, so that we can still easily use computer for other tasks
import os
num_cpus = len(os.sched_getaffinity(0)) - 2
import sys

df = pd.read_csv(sys.argv[1], sep =',')

smis = df['canonical_smiles'].tolist()# using ChEMBL provided SMILES

from itertools import combinations
smis_subsets = list(combinations(smis,2))

molObjects = {}
for smi in smis:
    molObjects.update({smi: Chem.MolFromSmiles(smi)})

subsets = {}
for i, (smi1,smi2) in enumerate(smis_subsets):
    field = {}
    field["smi1"] = smi1
    subsets[i] = field
    
    field["smi2"] = smi2
    subsets[i] = field
len(subsets)

mol_tuples = []
for key, value in subsets.items():
    mol_tuples.append((molObjects[value["smi1"]],molObjects[value["smi2"]], key))

def tc_mcs(mol1,mol2,key):
    # get maximum common substructure instance
    mcs = rdFMCS.FindMCS([mol1,mol2],timeout=10) # adding a 10 second timeout
    
    # get number of common bonds
    mcs_bonds = mcs.numBonds
    
    # get number of bonds for each
    # default is only heavy atom bonds
    mol1_bonds = mol1.GetNumBonds()
    mol2_bonds = mol2.GetNumBonds()
    
    # compute MCS-based Tanimoto
    tan_mcs = mcs_bonds / (mol1_bonds + mol2_bonds - mcs_bonds)
    return key, tan_mcs

from multiprocessing import Pool

if __name__ == '__main__':
    with Pool(num_cpus) as p:
        star_map = p.starmap(tc_mcs, mol_tuples)
    for key, tan_mcs in star_map:
        subsets[key].update({"tan_mcs": round(tan_mcs,4)})

outLines = []

outLines.append('        <edges count="' + str(len(smis_subsets)) + '">\n')

for i, subset in enumerate(subsets.values(), start=1):
    outLines.append(f'            <edge id="{i}" source="{subset["smi1"]}" target="{subset["smi2"]}"  weight="{subset["tan_mcs"]}"/>\n')


outLines.append('        </edges>\n')

print("".join(outLines), end = "")
