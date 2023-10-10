package ifam.edu.br.pdm.listacompras

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val itemsAdapter = ItemsAdapter()
        recyclerView.adapter = itemsAdapter

        val button = findViewById<Button>(R.id.buttonAdd)
        val editText = findViewById<EditText>(R.id.editTextItem)

        editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                adicionarItem(editText, itemsAdapter)
                true
            } else {
                false
            }
        }

        button.setOnClickListener {
            adicionarItem(editText, itemsAdapter)
        }
    }

    private fun adicionarItem(editText: EditText, adapter: ItemsAdapter) {
        // Verifica se a caixa de texto está vazia
        if (editText.text.isEmpty()) {
            editText.error = "Insira um Produto!"
        }

        val item = ItemModel(
            name = editText.text.toString(),
            onRemove = {
                adapter.removeItem(it)
            }

        )
        adapter.addItem(item)
        //Limpar a caixa de texto
        editText.text.clear()
        editText.requestFocus()
    }
}