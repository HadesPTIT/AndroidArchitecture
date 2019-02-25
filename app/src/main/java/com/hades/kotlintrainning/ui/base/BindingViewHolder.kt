package com.hades.kotlintrainning.ui.base

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

open class BindingViewHolder<T : ViewDataBinding>(var binding: T) : RecyclerView.ViewHolder(binding.root)
