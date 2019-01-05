package com.hades.kotlintrainning.login

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.hades.kotlintrainning.R
import com.hades.kotlintrainning.base.BaseDataBindingActivity
import com.hades.kotlintrainning.databinding.ActivityLoginBinding
import com.hades.kotlintrainning.movie.MovieActivity
import com.hades.kotlintrainning.todo.TodoActivity
import com.hades.kotlintrainning.utils.OnSwipeTouchListener
import com.hades.kotlintrainning.utils.setImageViewResource
import com.hades.kotlintrainning.viewmodel.LoginViewModel

class LoginActivity : BaseDataBindingActivity<ActivityLoginBinding>() {

    private lateinit var viewModel: LoginViewModel

    private var isLeft = false

    override val layoutID: Int
        get() = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
    }

    override fun initData() {
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        mBinding.model = viewModel
        mBinding.ctx = this
        setupEvent()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupEvent() {
        viewModel.mLoginEvent.observe(this, Observer<String> {
            when (it) {
                LoginViewModel.ERR_USER -> showToast("Please enter valid username")
                LoginViewModel.ERR_PWD -> showToast("Please enter valid password")
                LoginViewModel.SUCCESS -> {
                    showToast("Login success")
                    Handler().postDelayed({
                        startActivity(Intent(this@LoginActivity, TodoActivity::class.java))
                    }, 1000)
                }
                LoginViewModel.MOVIE -> {
                    Handler().postDelayed({
                        startActivity(Intent(this@LoginActivity, MovieActivity::class.java))
                    }, 200)
                }
            }
        })


        mBinding.imageView.setOnTouchListener(object : OnSwipeTouchListener(applicationContext) {

            override fun onSwipeRight() {
                swipe()
            }

            override fun onSwipeLeft() {
                swipe()
            }
        })
    }

    fun swipe() {
        if (isLeft) {
            mBinding.imageView.setImageResource(R.drawable.good_night_img)
            mBinding.textView.text = getString(R.string.text_night)
        } else {
            mBinding.imageView.setImageResource(R.drawable.good_morning_img)
            mBinding.textView.text = getString(R.string.text_morning)
        }
        isLeft = !isLeft
    }

}