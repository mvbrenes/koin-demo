package com.marcobrenes.koindemo

import com.marcobrenes.koindemo.model.Currency

object CurrencyFactory {

    fun makeCurrency(): Currency {
        return with(DataFactory) {
            Currency(
                randomInt(),
                randomString(),
                randomString(),
                randomString()
            )
        }
    }

    fun makeCurrencyList(count: Int): List<Currency> {
        return (0..count).map { makeCurrency() }
    }

}