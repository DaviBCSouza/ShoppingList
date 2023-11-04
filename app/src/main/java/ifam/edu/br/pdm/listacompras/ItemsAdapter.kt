package ifam.edu.br.pdm.listacompras

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    private var items = listOf<ItemModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newItems: List<ItemModel>) {
        items = newItems
        // Avisa a lista que existem itens novos e que ela deve recarregar
        notifyDataSetChanged()
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView = view.findViewById<TextView>(R.id.textView)
        private val button = view.findViewById<ImageButton>(R.id.imageButton)

        // Receber os dados e exibi-los na tela
        fun bind(item: ItemModel) {
            textView.text = item.name

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