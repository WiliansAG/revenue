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
                INSTANCE = Retrofit.Builder()
                    .baseUrl("http://www.recipepuppy.com/")
                    .client(getClient(true))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return INSTANCE
        }

    val instanceWithoutAuth: Retrofit?
        get() {
            if (INSTANCE_WITHOUT_AUTH == null) {
                INSTANCE_WITHOUT_AUTH = Retrofit.Builder()
                    .baseUrl("http://www.recipepuppy.com/")
                    .client(getClient(false))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return INSTANCE_WITHOUT_AUTH
        }

    fun getClient(withAuth: Boolean): OkHttpClient {
        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)
        val builder = OkHttpClient.Builder()
        builder.cookieJar(JavaNetCookieJar(cookieManager))
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        builder.addInterceptor(loggingInterceptor)
        builder.connectTimeout(30, TimeUnit.SECONDS)
        builder.readTimeout(30, TimeUnit.SECONDS)
        builder.writeTimeout(30, TimeUnit.SECONDS)
        if (withAuth) {
            builder.addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .header(
                        "Authorization",
                        if (token != null) token else ""
                    )
                    .header("Accept", "application/json")
                    .method(original.method(), original.body())
                    .build()
                chain.proceed(request)
            }
            //            builder.authenticator(new TokenAthenticator());
        }
        return builder.build()
    }

    fun updateToken(token: String?) {
        RetrofitClient.token = String.format("Bearer %s", token)
        INSTANCE = null
    }
}