package ifam.edu.br.pdm.listacompras

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    private val itens = mutableListOf<ItemModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    fun addItem(newItem: ItemModel) {
        itens.add(newItem)
        // Avisa a lista que existem itens novos e que ela deve recarregar
        notifyItemInserted(itens.size - 1)
    }

    fun removeItem(item: ItemModel) {
        val position = itens.indexOf(item)
        if (position != -1) {
            itens.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount(): Int = itens.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itens[position]
        holder.bind(item)
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