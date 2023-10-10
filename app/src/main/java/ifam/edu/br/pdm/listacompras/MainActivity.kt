package ifam.edu.br.pdm.listacompras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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
        button.setOnClickListener {
            val itemName = editText.text.toString().trim()

            if (itemName.isNotEmpty()) {
                val item = ItemModel(name = itemName)
                itemsAdapter.addItem(item)
                editText.text.clear()
                editText.requestFocus()
            } else {
                Toast.makeText(this, "A caixa de texto está vazia", Toast.LENGTH_SHORT).show()
            }
        }
    }
}