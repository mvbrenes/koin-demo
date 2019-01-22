package com.marcobrenes.koindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.marcobrenes.koindemo.presentation.CurrenciesAdapter
import com.marcobrenes.koindemo.presentation.CurrenciesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.ext.android.bindScope
import org.koin.androidx.scope.ext.android.getOrCreateScope
import org.koin.androidx.scope.ext.android.getScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity() {

    private val currenciesAdapter: CurrenciesAdapter by inject()
    private val currenciesViewModel: CurrenciesViewModel by viewModel {
        val currenciesJson = resources.openRawResource(R.raw.currencies)
            .bufferedReader().use { it.readText() }
        parametersOf(currenciesJson)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // bindscope to lifecycle owner, destroys on onDestroy
        bindScope(getOrCreateScope("browse"))

        setupCurrenciesRecyclerView()

        currenciesViewModel.liveData.observe(this, Observer {
                currenciesAdapter.currencies = it ?: emptyList()
        })

        if (currenciesViewModel.liveData.value.isNullOrEmpty()) {
            currenciesViewModel.retrieveCurrencies()
        }
    }
    private fun setupCurrenciesRecyclerView() {
        recycler_View.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = currenciesAdapter
        }
    }
}
