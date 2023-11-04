package ifam.edu.br.pdm.listacompras

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val viewModel: ItemsViewModel by viewModels()

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
                adicionarItem(editText)
                true
            } else {
                false
            }
        }

        button.setOnClickListener {
            adicionarItem(editText)
        }

        viewModel.itemsLiveData.observe(this) { items ->
            itemsAdapter.updateItems(items)
        }
    }

    private fun adicionarItem(editText: EditText) {
        // Verifica se a caixa de texto está vazia
        if (editText.text.isEmpty()) {
            editText.error = "Insira um Produto!"
        } else {
            val hide = getSystemService<InputMethodManager>()

            viewModel.addItem(editText.text.toString())
            //Limpar a caixa de texto
            editText.text.clear()
            editText.requestFocus()
            hide?.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }
}