package com.myapplication.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ListItem1Binding

class CryptoListAdapter(
    private val dataset: List<Cryptos>,
    private val onItemClicked: (Cryptos) -> Unit
) : RecyclerView.Adapter<CryptoListAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ListItem1Binding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ListItem1Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = dataset.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        holder.binding.coinLogoIMG.setImageResource(item.logo)
        holder.binding.coinNameTV.text = item.name
        holder.binding.coinPriceTV.text = item.price
        holder.binding.coinPercentTv.text = item.percent.toString()
        holder.binding.coinAmountTv.text = item.amount
        holder.binding.coinValueTV.text = item.value

        // Setzt die Textfarbe basierend auf dem Prozentwert (grün wenn <0, rot wenn >0)
        val percent = item.percent
        if (percent < 0) {
            holder.binding.coinPercentTv.setTextColor(holder.binding.root.context.getColor(R.color.red))
        } else {
            holder.binding.coinPercentTv.setTextColor(holder.binding.root.context.getColor(R.color.green))
        }


        // Set click listener
        holder.itemView.setOnClickListener { onItemClicked(item) }
        // OnClickListener für Listenelement, der Lambda 'onItemClicked' aufruft und aktuelle Krypto-Objekt 'item' übergibt, wenn das Listenelement geklickt wird.


    }
}
