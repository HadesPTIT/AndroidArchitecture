package com.hades.kotlintrainning.data

import com.hades.kotlintrainning.MyApplication
import com.hades.kotlintrainning.data.api.ApiService
import com.hades.kotlintrainning.data.api.ServiceGenerator

open class BaseRepository {

    protected val apiService : ApiService = ServiceGenerator.createService()
    protected val appDatabase : AppDatabase = MyApplication.instance!!.getDatabase()

}