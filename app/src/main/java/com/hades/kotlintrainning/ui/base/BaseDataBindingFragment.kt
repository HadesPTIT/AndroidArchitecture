package com.hades.kotlintrainning.ui.base

import android.arch.lifecycle.LifecycleObserver
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseDataBindingFragment<V : ViewDataBinding> : BaseFragment(), LifecycleObserver {

    lateinit var mBinding: V

    abstract val layoutID: Int

    abstract fun initData()

    fun bind(): V {
        return mBinding
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, layoutID, container, false)
        mBinding.setLifecycleOwner(this)
        mBinding.setLifecycleOwner(this)
        lifecycle.addObserver(this)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }


}
