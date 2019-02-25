package com.hades.kotlintrainning.ui.movie

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.GridLayoutManager
import com.hades.kotlintrainning.R
import com.hades.kotlintrainning.ui.base.BaseDataBindingActivity
import com.hades.kotlintrainning.ui.base.BaseViewAdapter
import com.hades.kotlintrainning.ui.base.SingleTypeAdapter
import com.hades.kotlintrainning.data.entity.Movie
import com.hades.kotlintrainning.databinding.ActivityMovieBinding
import com.hades.kotlintrainning.utils.Navigation
import com.hades.kotlintrainning.viewmodel.MovieViewModel

class MovieActivity : BaseDataBindingActivity<ActivityMovieBinding>(), NavigatorListener {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieAdapter: SingleTypeAdapter<Movie>

    override val layoutID: Int get() = R.layout.activity_movie

    override fun initData() {
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        movieAdapter = SingleTypeAdapter(this, R.layout.item_movie)
        mBinding.recyclerMovie.layoutManager = GridLayoutManager(this, 2)
        mBinding.recyclerMovie.adapter = movieAdapter
        mBinding.navigator = this
        movieViewModel.getListMovie(1)
        movieAdapter.presenter = ItemClickListener()
        initEvent()
    }

    private fun initEvent() {
        movieViewModel.getMovies().observe(this, Observer {
            it?.let { it1 ->
                movieAdapter.set(it1)
            }
        })
        movieViewModel.loadingLiveData.observe(this, Observer {
            it?.let {
                showLoading(it)
            }
        })

        movieViewModel.isInserted().observe(this, Observer {
            it?.let {
                showToast("Insert Success")
            }
        })

        movieViewModel.mFavoriteMovies.observe(this, Observer {
            it?.let {
                showToast("There are ${it.count()} movies !")
            }
        })

    }

    override fun onGoBack() {
        finish()
    }

    override fun onShowListFavorite() {
        movieViewModel.getFavoriteMovies()
    }


    inner class ItemClickListener : BaseViewAdapter.Presenter {

        fun onItemClick(movie: Movie) {
            // TODO : Transitions options android
//            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                this@MovieDetailActivity, childView, TRANSITION_IMAGE_NAME)
            Navigation.navigateToScreen(this@MovieActivity,movie,null)
        }

        fun onAddItemToFavorite(movie: Movie) {
            if (movieViewModel.isInFavorite(movie.id.toLong())) {
                movieViewModel.deleteMovieFromFavorite(movie)
            } else {
                movieViewModel.insertMovieToFavorite(movie)
            }
        }

    }

}