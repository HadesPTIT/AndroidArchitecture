package com.hades.kotlintrainning.ui.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater

import com.hades.kotlintrainning.BR

abstract class BaseViewAdapter<T>(context: Context) : RecyclerView.Adapter<BindingViewHolder<*>>() {

    var inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    var collection: MutableList<T>? = null
    var presenter: Presenter? = null
    var decorator: Decorator? = null

    override fun onBindViewHolder(bindingViewHolder: BindingViewHolder<*>, position: Int) {
        val item = collection!![position]
        bindingViewHolder.binding.setVariable(BR.item, item)
        bindingViewHolder.binding.setVariable(BR.listener, presenter)
        bindingViewHolder.binding.executePendingBindings()
        if (decorator != null) {
            decorator!!.decorator(bindingViewHolder, position, getItemViewType(position))
        }
    }

    override fun getItemCount(): Int {
        return if (collection == null) 0 else collection!!.size
    }

    fun remove(position: Int) {
        collection!!.removeAt(position)
        notifyItemRemoved(position)
    }

    fun remove(`object`: T) {
        collection!!.remove(`object`)
        notifyDataSetChanged()
    }

    fun clear() {
        collection!!.clear()
        notifyDataSetChanged()
    }

    /**
     * Listening item click...
     */
    interface Presenter

    /**
     * For ItemViewType
     */
    interface Decorator {
        fun decorator(holder: BindingViewHolder<*>, position: Int, viewType: Int)
    }
}
