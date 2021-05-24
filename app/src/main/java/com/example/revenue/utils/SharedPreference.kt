package com.example.revenue.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.revenue.data.models.RevenueModel
import com.google.gson.Gson
import org.json.JSONArray

class PreferencesManeger(context: Context){
    private val PREF = "revenue"
    private val preferences: SharedPreferences = context.getSharedPreferences(PREF,Context.MODE_PRIVATE)

    var setUser: String?
        get() = preferences.getString("revenue", "")
        set(value) = preferences.edit().putString("revenue", value).apply()

    fun saveFavorite(favoriteItem: RevenueModel){
        val gson = Gson()
        val json = gson.toJson(favoriteItem)
        var teste = preferences.edit().putString("favoriteItem",json)
        val te = teste
    }

    fun getFavorite(){
        try {
            val response = preferences.getString("favoriteItem", "[]")
            val gson = Gson()
            val t = gson.fromJson(response,RevenueModel::class.java)
            val a = t

        }catch (e: Exception) {
            e.printStackTrace()
        }
    }
}