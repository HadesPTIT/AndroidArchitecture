package com.hades.kotlintrainning.viewmodel

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.text.TextUtils
import com.hades.kotlintrainning.base.BaseViewModel

class LoginViewModel(application: Application) : BaseViewModel(application) {

    var mUserName = MutableLiveData<String>()
    var mPassWord = MutableLiveData<String>()
    var mLoginEvent = MutableLiveData<String>()

    companion object {
        const val SUCCESS = "SUCCESS"
        const val ERR_USER = "ERR_USER"
        const val ERR_PWD = "ERR_PWD"
        const val MOVIE = "MOVIE"
    }

    fun validate() : Boolean {
        if (mUserName.value.isNullOrBlank()) {
            mLoginEvent.postValue(ERR_USER)
            return false
        }
        if (mPassWord.value.isNullOrBlank()) {
            mLoginEvent.postValue(ERR_PWD)
            return false
        }
        mLoginEvent.postValue(SUCCESS)
        return true
    }

    fun login() {
        if (!validate()) {
            return
        }
        // TODO : login logic here
    }

    fun movie() {
        mLoginEvent.postValue(MOVIE)
    }
}