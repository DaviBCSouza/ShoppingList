package ifam.edu.br.pdm.listacompras

data class ItemModel(val name: String, val onRemove: (ItemModel) -> Unit)