package com.hades.kotlintrainning.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hades.kotlintrainning.databinding.ItemNetworkStateBinding
import com.hades.kotlintrainning.movie.MovieActivity

class NetworkStateViewHolder(
    private val binding: ItemNetworkStateBinding,
    private val mListener: MovieActivity.ItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bindView() {
        binding.apply {
            listener = mListener
            executePendingBindings()
        }
    }

    companion object {

        fun create(parent: ViewGroup, listener: MovieActivity.ItemClickListener): NetworkStateViewHolder {
            val binding = ItemNetworkStateBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return NetworkStateViewHolder(binding, listener)
        }
    }
}