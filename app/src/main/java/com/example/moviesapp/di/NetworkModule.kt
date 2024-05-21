package com.example.moviesapp.di

import android.content.Context
import com.example.moviesapp.common.NetworkConnectivityLiveData
import com.example.moviesapp.data.apiservice.ApiManager
import com.example.moviesapp.data.apiservice.ApiService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideNetworkConnectivity(@ApplicationContext context: Context): NetworkConnectivityLiveData = NetworkConnectivityLiveData(context)

    @Singleton
    @Provides
    fun providesCoroutineDispatcher() : CoroutineDispatcher = Dispatchers.IO

    @Singleton
    @Provides
    fun providesOkHttp() : OkHttpClient = OkHttpClient()
        .newBuilder()
        .build()

    @Singleton
    @Provides
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient) : Retrofit = Retrofit
        .Builder()
        .baseUrl("https://api.provideyourapi.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
        .build()

    @Singleton
    @Provides
    fun providesRetrofit(retrofit: Retrofit) : ApiManager = ApiManager(retrofit)

    @Singleton
    @Provides
    fun providesApiService(apiManager: ApiManager) : ApiService = apiManager.apiService
}