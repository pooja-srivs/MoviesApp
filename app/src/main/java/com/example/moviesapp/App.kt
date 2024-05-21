package com.example.moviesapp

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.moviesapp.common.NetworkConnectivityLiveData
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application(){

    @Inject
    lateinit var connectivityLiveData: NetworkConnectivityLiveData

    fun getNetworkStatus(): LiveData<Boolean> {
        return connectivityLiveData
    }
}