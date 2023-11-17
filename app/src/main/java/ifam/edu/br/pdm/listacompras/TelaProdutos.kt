package ifam.edu.br.pdm.listacompras

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TelaProdutos : AppCompatActivity() {
    private val viewModel: ItemsViewModel by viewModels {
        ItemsViewModelFactory(applicationContext)
    }

    private val itemsAdapter = ItemsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_produtos)

        val button = findViewById<Button>(R.id.buttonAdicionar)
        val textViewTotal = findViewById<TextView>(R.id.textViewTotal)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewItems)
        recyclerView.adapter = itemsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val dados = intent.extras
        val imagem = dados?.getString("imagem")


        if (dados != null) {
            val imagemUri = Uri.parse(imagem)
            val nome = dados.getString("nome")
            val quantidade = dados.getString("quantidade")
            val valor = dados.getString("valor")

            viewModel.addItem(
                imagemUri,
                "$nome",
                "$quantidade",
                "$valor"

            )
        }

        viewModel.totalLiveData.observe(this) { total ->
            textViewTotal.text = getString(R.string.novoTotal, total)
        }

        viewModel.itemsLiveData.observe(this) { items ->
            itemsAdapter.updateItems(items)
        }

        button.setOnClickListener {
            toTelaCadastrar()
        }
    }

    private fun toTelaCadastrar() {
        val telaCadastrar = Intent(this, TelaCadastrar::class.java)
        startActivity(telaCadastrar)
    }
}