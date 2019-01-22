package com.marcobrenes.koindemo.data

import com.marcobrenes.koindemo.model.Currency

interface DataRepository {
    fun getCurrencies(jsonString: String): List<Currency>
}