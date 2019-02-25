package com.hades.kotlintrainning.ui.base

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.view.ViewGroup

import java.util.ArrayList

class SingleTypeAdapter<T> constructor(context: Context, @get:LayoutRes val layoutRes: Int) : BaseViewAdapter<T>(context) {

    init {
        collection = ArrayList()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BindingViewHolder<ViewDataBinding> {
        return BindingViewHolder(DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutRes, p0, false))
    }

    fun add(viewModel: T) {
        collection!!.add(viewModel)
        notifyDataSetChanged()
    }

    fun addAll(viewModels: List<T>) {
        collection!!.addAll(viewModels)
        notifyDataSetChanged()
    }

    fun set(viewModels: List<T>) {
        collection!!.clear()
        addAll(viewModels)
    }

    fun getItem(position: Int): T? {
        try {
            return collection!![position]
        } catch (e: IndexOutOfBoundsException) {
            e.printStackTrace()
            return null
        }

    }


    interface Presenter<T> : BaseViewAdapter.Presenter {

        fun onItemClick(item: T)

    }

}
