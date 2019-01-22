package com.marcobrenes.koindemo


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.marcobrenes.koindemo.data.DataRepository
import com.marcobrenes.koindemo.model.Currency
import com.marcobrenes.koindemo.presentation.CurrenciesAdapter
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.loadKoinModules
import org.koin.standalone.inject
import org.koin.test.KoinTest
import org.koin.test.declareMock

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest : KoinTest {

    private val mockDataRepository: DataRepository = mock()

    @get:Rule val activity = ActivityTestRule<MainActivity>(
        MainActivity::class.java,
        false,
        false
    )

    @Before fun setup() {
        loadKoinModules(module(override = true) {
            factory { mockDataRepository }
        })
    }

    @Test fun activityLaunches() {
        activity.launchActivity(null)
        onView(withId(R.id.recycler_View))
            .check(matches(isDisplayed()))
    }

    @Test fun checkCurrenciesDisplay() {
        // Given
        val currencies = CurrencyFactory.makeCurrencyList(5)
        stubDataRepositoryGetCurrencies(currencies)
        // When
        activity.launchActivity(null)
        // Then
        checkCurrenciesAreDisplayed(currencies)
    }

    private fun checkCurrenciesAreDisplayed(currencies: List<Currency>) {
        currencies.forEachIndexed { index, currency ->
            onView(withId(R.id.recycler_View))
                .perform(RecyclerViewActions.scrollToPosition<CurrenciesAdapter.ViewHolder>(index))
            onView(withId(R.id.recycler_View))
                .check(matches(hasDescendant(withText(currency.name))))
        }
    }

    private fun stubDataRepositoryGetCurrencies(currencies: List<Currency>) {
        whenever(mockDataRepository.getCurrencies(any())) doReturn currencies
    }

}
