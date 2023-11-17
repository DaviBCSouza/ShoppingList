package ifam.edu.br.pdm.listacompras

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class TelaCadastrar : AppCompatActivity() {

    private lateinit var uploadButton: ImageView
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_cadastrar)

        uploadButton = findViewById(R.id.imageUpload)
        val editTextItem = findViewById<EditText>(R.id.editTextItem)
        val editTextQtd = findViewById<EditText>(R.id.editTextQtd)
        val editTextValor = findViewById<EditText>(R.id.editTextValor)
        val button = findViewById<Button>(R.id.buttonInserir)

        uploadButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(Intent.createChooser(intent, "Escolha sua imagem"), 1)
        }

        button.setOnClickListener {
            // Verifica se a caixa de texto está vazia
            if (editTextItem.text.isEmpty()) {
                editTextItem.error = "Insira um Produto!"
            } else if (editTextQtd.text.isEmpty()) {
                editTextQtd.error = "Insira a Quantidade de Itens!"
            } else if (editTextValor.text.isEmpty()) {
                editTextValor.error = "Insira um Valor para o Produto!"
            } else {
                val amount = editTextQtd.text.toString().toDouble()
                val value = editTextValor.text.toString().toDouble()

                val result = amount * value

                editTextValor.setText(result.toString())

                toTelaProdutos(
                    editTextItem.text.toString(),
                    editTextQtd.text.toString(),
                    editTextValor.text.toString()
                )

                //Limpar a caixa de texto
                editTextItem.text.clear()
                editTextQtd.text.clear()
                editTextValor.text.clear()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1 && data != null) {
                imageUri = data.data
                uploadButton.setImageURI(imageUri)
            }
        }
    }

    private fun toTelaProdutos(name: String, amount: String, value: String) {
        val telaProdutos = Intent(this, TelaProdutos::class.java)
        telaProdutos.putExtra("imagem", imageUri.toString())
        telaProdutos.putExtra("nome", name)
        telaProdutos.putExtra("quantidade", amount)
        telaProdutos.putExtra("valor", value)
        startActivity(telaProdutos)
    }
}