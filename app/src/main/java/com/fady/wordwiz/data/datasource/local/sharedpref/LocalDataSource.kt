package com.fady.wordwiz.data.datasource.local.sharedpref

import android.content.SharedPreferences
import com.google.gson.Gson

object LocalDataSource {

    const val FILE_PREFERENCES = "wordwiz_prefernce"
    val gson = Gson()
    private var preferences: SharedPreferences? = null

    fun initialize(preferences: SharedPreferences?) {
        LocalDataSource.preferences = preferences
    }

    var isConnected: Boolean
        get() = preferences.getSafeString(SharedPreferencesKeys.IS_CONNECTED.value).toBoolean()
        set(value) = preferences.storeObject(SharedPreferencesKeys.IS_CONNECTED.value, value)


}

