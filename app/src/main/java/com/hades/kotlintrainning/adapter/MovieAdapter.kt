package com.hades.kotlintrainning.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.recyclerview.extensions.AsyncDifferConfig
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.hades.kotlintrainning.R
import com.hades.kotlintrainning.data.api.NetworkState
import com.hades.kotlintrainning.data.entity.Movie
import com.hades.kotlintrainning.movie.MovieActivity
import java.util.concurrent.Executors

class MovieAdapter(private val listener: MovieActivity.ItemClickListener) :
    PagedListAdapter<Movie, RecyclerView.ViewHolder>(diffUtilCallback) {

    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            R.layout.item_network_state -> {
                NetworkStateViewHolder.create(parent, listener)
            }
            R.layout.item_movie -> {
                MovieViewHolder.create(parent, listener)
            }
            else -> {
                throw IllegalArgumentException("Unknown view type: $viewType")
            }
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_movie -> {
                (holder as MovieViewHolder).bindView(getItem(position)!!)
            }
            R.layout.item_network_state -> {
                (holder as NetworkStateViewHolder).bindView()
            }
            else -> {
                throw IllegalArgumentException("Unknown view type: ${getItemViewType(position)}")
            }
        }
    }

    override fun getItemViewType(position: Int): Int =
        if (hasExtraRow() && position == itemCount - 1) {
            R.layout.item_network_state
        } else {
            R.layout.item_movie
        }

    override fun getItemCount(): Int = super.getItemCount() + if (hasExtraRow()) 1 else 0

    private fun hasExtraRow() =
        networkState != null && networkState != NetworkState.SUCCESS && networkState != NetworkState.RUNNING

    fun setNetworkState(newNetworkState: NetworkState?) {
        val previousState = networkState
        val hadExtraRow = hasExtraRow()
        networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hasExtraRow) {
                // We add retry_item to the end of list because now, we don't have network.
                notifyItemInserted(super.getItemCount())
            } else {
                // We currently have retry_item in end of list. Now, load success so remove it
                notifyItemRemoved(itemCount - 1)
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            // newNetworkState is Running or Failed, previousState is SUCCESS
            notifyItemChanged(itemCount - 1)
        }
    }

    companion object {

        private val POPULAR_MOVIE_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {

            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.title == newItem.title
                        && oldItem.vote_average == newItem.vote_average
                        && oldItem.release_date == newItem.release_date
        }

        val diffUtilCallback = AsyncDifferConfig.Builder<Movie>(POPULAR_MOVIE_COMPARATOR)
            .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
            .build()
    }

}