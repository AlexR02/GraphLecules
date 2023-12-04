import sys 
from rdkit import Chem
from rdkit.Chem import Draw

# SMILES da molécula que você deseja desenhar
smiles = sys.argv[1]

# Parse do SMILES para um objeto de molécula do RDKit
mol = Chem.MolFromSmiles(smiles)

# Desenha a molécula e salva como uma imagem PNG
img = Draw.MolToImage(mol, size=(500,500))

# Salva a imagem como um arquivo PNG
img.save(sys.argv[2] + sys.argv[3] + "_mol_structure.png")
print(sys.argv[2] + sys.argv[3] + "_mol_structure.png")