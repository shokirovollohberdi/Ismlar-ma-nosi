package com.example.myapplication.cash

import android.content.Context
import android.content.SharedPreferences
import com.example.myapplication.models.Properties
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object MySharedPreferences {
    private const val NAME = "KeshXotiraga"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var obektString: ArrayList<Properties>
        get() = gsonStringToArray(preferences.getString("obekt", "[]")!!)
        set(value) = preferences.edit {
            if (value != null) {
                it.putString("obekt", arrayToGsonString(value))
            }
        }

    fun arrayToGsonString(arrayList: ArrayList<Properties>): String {
        return Gson().toJson(arrayList)
    }

    fun gsonStringToArray(gsonString: String): ArrayList<Properties> {
        val typeToken = object : TypeToken<ArrayList<Properties>>() {}.type
        return Gson().fromJson(gsonString, typeToken)
    }
}