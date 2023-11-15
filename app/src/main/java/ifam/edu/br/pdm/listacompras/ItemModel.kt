package ifam.edu.br.pdm.listacompras

import ifam.edu.br.pdm.listacompras.data.ItemEntity

data class ItemModel(val id: Long, val name: String, val onRemove: (ItemModel) -> Unit) {

    fun toEntity(): ItemEntity {
        return ItemEntity(id = this.id, name = this.name)
    }
}