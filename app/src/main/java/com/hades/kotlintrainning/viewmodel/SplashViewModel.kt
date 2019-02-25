package com.hades.kotlintrainning.viewmodel

import android.app.Application

import com.hades.kotlintrainning.ui.base.BaseViewModel

class SplashViewModel(application: Application) : BaseViewModel(application) {

//    private val mUserRepo = UserRepository

    val isLoggedIn: Boolean get() = true

}
