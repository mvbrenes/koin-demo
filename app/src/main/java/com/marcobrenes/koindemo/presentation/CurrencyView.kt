package com.marcobrenes.koindemo.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.marcobrenes.koindemo.R
import com.marcobrenes.koindemo.UrlHelper
import com.marcobrenes.koindemo.model.Currency
import kotlinx.android.synthetic.main.item_currency.view.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class CurrencyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), KoinComponent {

    private val urlHelper: UrlHelper by inject()

    init {
        View.inflate(context, R.layout.item_currency, this)
    }

    fun setCurrency(currency: Currency) {
        name_textview.text = currency.name
        symbol_textview.text = currency.symbol

        setOnClickListener {
            urlHelper.launchCurrencyUrl(context, currency.slug)
        }
    }
}