package ifam.edu.br.pdm.listacompras.data

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import ifam.edu.br.pdm.listacompras.ItemModel

@Entity
data class ItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val image: Uri,
    val name: String,
    val amount: String,
    val value: String
) {

    // Função de extensão que faz o mapeamento de um objeto ItemEntity para o objeto Item Model
    fun toModel(onRemove: (ItemModel) -> Unit): ItemModel {
        return ItemModel(
            id = this.id,
            image = this.image,
            name = this.name,
            amount = this.amount,
            value = this.value,
            onRemove = onRemove
        )
    }
}