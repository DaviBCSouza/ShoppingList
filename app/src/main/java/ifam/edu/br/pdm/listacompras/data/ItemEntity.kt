package ifam.edu.br.pdm.listacompras.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import ifam.edu.br.pdm.listacompras.ItemModel

@Entity
class ItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String
) {

    // Função de extensão que faz o mapeamento de um objeto ItemEntity para o objeto Item Model
    fun ItemEntity.toModel(onRemove: (ItemModel) -> Unit): ItemModel {
        return ItemModel(name = this.name, onRemove = onRemove)
    }
}