package com.marcobrenes.koindemo.data

import com.google.gson.Gson
import com.marcobrenes.koindemo.model.Currency

class RemoteDataRepositoryImpl(private val gson: Gson) : DataRepository {

    override fun getCurrencies(jsonString: String): List<Currency> {
        return gson.fromJson(jsonString, Array<Currency>::class.java).toList()
    }
}