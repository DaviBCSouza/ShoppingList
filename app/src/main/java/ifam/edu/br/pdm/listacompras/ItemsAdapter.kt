package ifam.edu.br.pdm.listacompras

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView

class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    private var items = listOf<ItemModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newItems: List<ItemModel>) {
        items = newItems
        // Avisa a lista que houve alteração nos itens e que ela deve recarregar
        notifyDataSetChanged()
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView = view.findViewById<ImageView>(R.id.imageProduto)
        private val textViewItem = view.findViewById<TextView>(R.id.textItem)
        private val textViewQtd = view.findViewById<TextView>(R.id.textQtd)
        private val textViewValor = view.findViewById<TextView>(R.id.textValor)
        private val button = view.findViewById<ImageButton>(R.id.imageButton)

        // Receber os dados e exibi-los na tela
        fun bind(item: ItemModel) {
            imageView.setImageURI(item.image)

            textViewItem.text = item.name
            textViewQtd.text = itemView.context.getString(R.string.item_amount, item.amount)
            textViewValor.text = itemView.context.getString(R.string.item_value, item.value)

            textViewItem.typeface = ResourcesCompat.getFont(itemView.context, R.font.chewy)
            textViewQtd.typeface = ResourcesCompat.getFont(itemView.context, R.font.chewy)
            textViewValor.typeface = ResourcesCompat.getFont(itemView.context, R.font.chewy)


            button.setOnClickListener {
                // Criar uma caixa de diálogo com AlertDialog
                val confirmaRemove = AlertDialog.Builder(itemView.context)
                confirmaRemove.setTitle("Atenção!")
                confirmaRemove.setMessage("Tem certeza que deseja remover ${item.name} da lista?")
                confirmaRemove.setPositiveButton("Sim") { _, _ ->
                    item.onRemove(item)
                }
                confirmaRemove.setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.dismiss()
                }
                confirmaRemove.create().show()
            }
        }
    }
}