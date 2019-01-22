package com.marcobrenes.koindemo

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat

class UrlHelper(private val baseUrl: String) {

    fun launchCurrencyUrl(context: Context, slug: String) {
        launchUrl(context, Uri.parse("$baseUrl$slug"))
    }

    fun launchUrl(context: Context, uri: Uri) {
        CustomTabsIntent.Builder()
            .addDefaultShareMenuItem()
            .setToolbarColor(
                ContextCompat.getColor(context, R.color.colorPrimary)
            )
            .setShowTitle(true)
            .build()
            .launchUrl(context, uri)
    }
}