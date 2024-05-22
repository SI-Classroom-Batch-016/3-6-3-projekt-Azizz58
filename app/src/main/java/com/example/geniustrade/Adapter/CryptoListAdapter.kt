package com.example.geniustrade.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.geniustrade.data.data.Model.CryptoModel
import com.example.myapplication.R
import com.example.myapplication.databinding.ViewholderWalletBinding

class CryptoListAdapter(private val items: MutableList<CryptoModel>) : RecyclerView.Adapter<CryptoListAdapter.ViewHolder>() {

    class ViewHolder(val binding: ViewholderWalletBinding) : RecyclerView.ViewHolder(binding.root)

    private var formatter: DecimalFormat = DecimalFormat("###,###,###")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoListAdapter.ViewHolder {
        val context = parent.context
        val binding = ViewholderWalletBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CryptoListAdapter.ViewHolder, position: Int) {
        val item = items[position]
        val context = holder.itemView.context

        holder.binding.crypoNameTV.text = item.symbol
        holder.binding.cryptoPriceTV.text = "$" + formatter.format(item.price)
        holder.binding.changePercentTV.text = "${item.changePercent}%"
        holder.binding.propertySizeTV.text = "${item.amountNum}${item.shortSymbol.replace("/USDT", "")}"
        holder.binding.propertyAmountTV.text = "$" + formatter.format(item.amountDollar)

        if (item.changePercent < 0) {
            holder.binding.changePercentTV.setTextColor(ContextCompat.getColor(context, R.color.red))
        } else {
            holder.binding.changePercentTV.setTextColor(ContextCompat.getColor(context, R.color.green))
        }

        val drawableResourceId = context.resources.getIdentifier(item.symbolLogo, "drawable", context.packageName)
        Glide.with(context)
            .load(drawableResourceId)
            .into(holder.binding.logoIMG)
    }

    override fun getItemCount(): Int = items.size
}