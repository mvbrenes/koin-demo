package com.marcobrenes.koindemo

import com.google.gson.Gson
import com.marcobrenes.koindemo.data.DataRepository
import com.marcobrenes.koindemo.data.LocalDataRepositoryImpl
import com.marcobrenes.koindemo.presentation.CurrenciesAdapter
import com.marcobrenes.koindemo.presentation.CurrenciesViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val applicationModule = module {
    single { Gson() }
    single { UrlHelper(getProperty("currency_base_url")) }

    factory<DataRepository>{ LocalDataRepositoryImpl(gson = get()) }
}

val browseModule = module("browse") {
    factory { CurrenciesAdapter() }
    viewModel { (jsonString: String) -> CurrenciesViewModel(get(), jsonString) }
}