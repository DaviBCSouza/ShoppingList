package ifam.edu.br.pdm.listacompras

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import ifam.edu.br.pdm.listacompras.data.ItemsDatabase

@Suppress("UNCHECKED_CAST")
class ItemsViewModelFactory(private val applicationContext: Context) : ViewModelProvider.Factory {
    private fun createDatabase(): ItemsDatabase {
        return Room.databaseBuilder(
            applicationContext,
            ItemsDatabase::class.java, "items_database.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemsViewModel::class.java)) {
            return ItemsViewModel(database = createDatabase()) as T
        }
        throw IllegalArgumentException("Classe ViewModel Desconhecida")
    }
}