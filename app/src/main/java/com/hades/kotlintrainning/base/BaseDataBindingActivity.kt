package com.hades.kotlintrainning.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle

abstract class BaseDataBindingActivity<V : ViewDataBinding> : BaseActivity() {

    lateinit var mBinding: V

    abstract val layoutID: Int

    abstract fun initData()

    fun bind(): V {
        return mBinding
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, layoutID)

        /**
         * @method setLifecycleOwner( ) used for observing change of LiveData in this binding.
         * If a liveData is in one of the binding expressions and no LifecycleOwner is set then
         * the liveData will not be observer and update
         */
        mBinding.setLifecycleOwner(this)
        // Eg : check network update using liveData here

        initData()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
