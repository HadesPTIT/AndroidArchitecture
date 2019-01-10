package com.hades.kotlintrainning.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hades.kotlintrainning.data.entity.Movie
import com.hades.kotlintrainning.databinding.ItemMovieBinding
import com.hades.kotlintrainning.movie.MovieActivity

class MovieViewHolder(
    private val binding: ItemMovieBinding,
    private val mlistener: MovieActivity.ItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bindView(it: Movie) {
        binding.apply {
            item = it
            listener = mlistener
            executePendingBindings()
        }
    }

    companion object {

        fun create(parent: ViewGroup, listener: MovieActivity.ItemClickListener): MovieViewHolder {
            val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return MovieViewHolder(binding, listener)
        }
    }
}