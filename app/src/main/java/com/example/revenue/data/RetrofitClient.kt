package com.example.revenue.data

import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private var INSTANCE: Retrofit? = null
    private var INSTANCE_WITHOUT_AUTH: Retrofit? = null
    private var token: String? = null
    val instance: Retrofit?
        get() {
            if (INSTANCE == null) {
                val client = OkHttpClient.Builder().build()
                INSTANCE = Retrofit.Builder()
                    .baseUrl("http://www.recipepuppy.com/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return INSTANCE
        }


}