package com.picpay.desafio.android.ui.extension

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

fun Context.isInternetAvailable(): Boolean {
    var isConnected: Boolean = false
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    return isConnected
}