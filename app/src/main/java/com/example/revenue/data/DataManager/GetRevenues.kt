package com.example.revenue.data.DataManager

import android.content.Context
import android.util.Log
import com.example.revenue.data.DataCallbacks.RevenueCallback
import com.example.revenue.data.RetrofitClient
import com.example.revenue.data.models.RevenueModel
import com.example.revenue.data.remote.RevenueRequest
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.URLDecoder


object GetRevenues {
     fun getRevenue(context: Context?, page: Int,ingredient: String,query: String,callback: RevenueCallback) {
        RetrofitClient.getInstance()
            .create(RevenueRequest::class.java)
            .revenue(page,ingredient,query)
            ?.enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(
                    call: Call<ResponseBody?>,
                    response: Response<ResponseBody?>
                ) {
                    if (response.isSuccessful) {
                        val body:ResponseBody? = response.body()
                        if (body == null) {
                            callback.onFailure("erro")
                            return
                        }
                        try {
                            val jsonSucces = JSONObject(body.string())
                            val jsonResponse = jsonSucces.getJSONArray("results")
                            val gson = Gson();
                            val revenueList = ArrayList<RevenueModel>();

                            for(i in 0 until jsonResponse.length()-1){
                                val model = gson.fromJson(jsonResponse.getJSONObject(i).toString(),RevenueModel::class.java)
                                revenueList.add(model)
                            }
                            callback.onSuccess(revenueList)
                        } catch (e: JSONException) {
                            callback.onFailure(e.message)
                        } catch (e: IOException) {
                            callback.onFailure(e.message)
                        }
                    }
                }

                override fun onFailure(
                    call: Call<ResponseBody?>,
                    t: Throwable
                ) {
                    Log.v("RESPONSE", "NAO FOI")
                }
            })
    }
}