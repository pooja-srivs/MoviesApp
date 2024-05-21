package com.example.moviesapp.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import androidx.lifecycle.LiveData
import com.example.moviesapp.App
import javax.inject.Inject

class NetworkConnectivityLiveData @Inject constructor(mContext: Context): LiveData<Boolean>() {

    private val connectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            postValue(true)
        }

        override fun onLost(network: Network) {
            postValue(false)
        }
    }

    init {
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }

    override fun onActive() {
        super.onActive()
        postValue(isConnected())
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    private fun isConnected(): Boolean {
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }
}