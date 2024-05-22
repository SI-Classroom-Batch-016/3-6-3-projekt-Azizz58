package com.example.geniustrade.Adapter

import android.content.Context
import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.geniustrade.data.data.Model.CryptoModel
import com.example.myapplication.R
import com.example.myapplication.databinding.ViewholderWalletBinding

class CryptoListAdapter(private val items: MutableList<CryptoModel>): RecyclerView.Adapter<CryptoListAdapter.ViewHolder>(){
    class ViewHolder (val binding: ViewholderWalletBinding):RecyclerView.ViewHolder(binding.root)

    private lateinit var context:Context
    var formatter: DecimalFormat?=null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CryptoListAdapter.ViewHolder {
        context=parent.context
        formatter=DecimalFormat("###,###,###")
        val binding= ViewholderWalletBinding.inflate(LayoutInflater.from(context),parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CryptoListAdapter.ViewHolder, position: Int) {
        val item= items[position]
        holder.binding.crypoNameTV.text=item.symbol
        holder.binding.cryptoPriceTV.text= "$" + formatter?.format(item.price)
        holder.binding.changePercentTV.text=item.changePercent.toString()+"%"
        holder.binding.propertySizeTV.text= item.amountNum.toString()+item.shortSymbol.replace("/USDT","")
        holder.binding.propertyAmountTV.text="$"+formatter?.format(item.amountDollar)
        if (item.changePercent<0) holder.binding.changePercentTV.setText(context.resources.getColor(
            R.color.red))

        val drawableResourceId=holder.itemView.resources.getIdentifier(
            item.symbolLogo,
            "drawable",
            holder.itemView.context.packageName)

        Glide.with(context)
            .load(drawableResourceId)
            .into(holder.binding.logoIMG)

    }

    override fun getItemCount(): Int=items.size


}