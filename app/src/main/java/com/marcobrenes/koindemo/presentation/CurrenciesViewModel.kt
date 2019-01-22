package com.marcobrenes.koindemo.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marcobrenes.koindemo.data.DataRepository
import com.marcobrenes.koindemo.model.Currency

class CurrenciesViewModel constructor(
    private val dataRepository: DataRepository,
    private val jsonString: String
): ViewModel() {

    private val _liveData = MutableLiveData<List<Currency>>()
    val liveData: LiveData<List<Currency>> get() = _liveData

    fun retrieveCurrencies() {
        val data = dataRepository.getCurrencies(jsonString)
        _liveData.postValue(data)
    }
}
