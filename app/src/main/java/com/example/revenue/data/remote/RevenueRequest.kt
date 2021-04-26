package com.example.revenue.data.remote

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RevenueRequest {
    @GET("api/")
    fun revenue(@Query("p") page: Int,
                @Query("i") ingredient: String?,
                @Query("q") query: String?)
            : Call<ResponseBody?>?
}
