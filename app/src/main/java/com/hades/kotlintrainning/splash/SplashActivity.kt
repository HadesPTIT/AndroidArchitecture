package com.hades.kotlintrainning.splash

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Handler
import com.hades.kotlintrainning.R
import com.hades.kotlintrainning.base.BaseDataBindingActivity
import com.hades.kotlintrainning.databinding.ActivitySplashBinding
import com.hades.kotlintrainning.login.LoginActivity
import com.hades.kotlintrainning.viewmodel.SplashViewModel

class SplashActivity : BaseDataBindingActivity<ActivitySplashBinding>() {

    companion object {
        private val DELAY_TIME: Long = 2000
    }

    private var mSplashViewModel: SplashViewModel? = null


    override val layoutID: Int
        get() = R.layout.activity_splash

    override fun initData() {
        mSplashViewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)
        mBinding.model = mSplashViewModel
        goToMainActivity()
    }

    private fun goToMainActivity() {
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }, DELAY_TIME)
    }

}