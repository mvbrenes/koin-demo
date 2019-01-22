package com.marcobrenes.koindemo.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marcobrenes.koindemo.R
import com.marcobrenes.koindemo.model.Currency

class CurrenciesAdapter : RecyclerView.Adapter<CurrenciesAdapter.ViewHolder>() {

    var currencies: List<Currency> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_currency, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return currencies.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.currencyView.setCurrency(currencies[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val currencyView: CurrencyView = view.findViewById(R.id.view_currency)
    }

}