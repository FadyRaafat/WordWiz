package com.fady.wordwiz

import android.app.Application
import com.fady.wordwiz.data.datasource.local.sharedpref.LocalDataSource
import com.fady.wordwiz.data.datasource.local.sharedpref.LocalDataSource.FILE_PREFERENCES
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        LocalDataSource.initialize(getSharedPreferences(FILE_PREFERENCES, MODE_PRIVATE))
    }
}
