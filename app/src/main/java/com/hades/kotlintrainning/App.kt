package com.hades.kotlintrainning

import android.app.Application
import com.hades.kotlintrainning.data.AppDatabase

class App : Application() {

    companion object {
        var instance: App? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        // initialize for any
        // Use ApplicationContext.
        // example: SharedPreferences etc...
        // val context: Context = MainApplication.applicationContext()
    }

    fun getDatabase() : AppDatabase {
        return AppDatabase.getInstance(this)
    }

}