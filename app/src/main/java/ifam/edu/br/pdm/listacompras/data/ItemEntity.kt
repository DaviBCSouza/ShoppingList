package ifam.edu.br.pdm.listacompras.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import ifam.edu.br.pdm.listacompras.ItemModel

@Entity
data class ItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String
) {

    // Função de extensão que faz o mapeamento de um objeto ItemEntity para o objeto Item Model
    fun toModel(onRemove: (ItemModel) -> Unit): ItemModel {
        return ItemModel(id = this.id, name = this.name, onRemove = onRemove)
    }
}