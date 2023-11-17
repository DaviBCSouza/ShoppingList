package ifam.edu.br.pdm.listacompras

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ifam.edu.br.pdm.listacompras.data.ItemEntity
import ifam.edu.br.pdm.listacompras.data.ItemsDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemsViewModel(private val database: ItemsDatabase) : ViewModel() {
    val itemsLiveData = MutableLiveData<List<ItemModel>>()
    val totalLiveData = MutableLiveData<Double>()

    fun addItem(image: Uri, name: String, amount: String, value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val entity = ItemEntity(
                id = 0,
                image = image,
                name = name,
                amount = amount,
                value = value
            )
            database.itemsDAO().insert(entity)
            fetchAll()
        }
    }

    private fun removeItem(item: ItemModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val entity = item.toEntity()
            database.itemsDAO().delete(entity)
            fetchAll()
        }
    }

    private fun updateTotal(items: List<ItemModel>) {
        val total = items.sumOf { it.value.toDouble() }
        totalLiveData.postValue(total)
    }

    // Esta função buscará todos os registros do banco de dados
    private suspend fun fetchAll() {
        val result = database.itemsDAO().getAll().map {
            it.toModel(onRemove = ::removeItem)
        }

        itemsLiveData.postValue(result)
        updateTotal(result)
    }
}