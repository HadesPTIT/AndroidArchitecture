package com.hades.kotlintrainning.moviedetail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import com.hades.kotlintrainning.R
import com.hades.kotlintrainning.base.BaseDataBindingActivity
import com.hades.kotlintrainning.base.BaseViewModel
import com.hades.kotlintrainning.databinding.ActivityMovieDetailBinding
import com.hades.kotlintrainning.viewmodel.MovieDetailViewModel

class MovieDetailActivity : BaseDataBindingActivity<ActivityMovieDetailBinding>() {

    private lateinit var movieDetailViewModel: MovieDetailViewModel

    override val layoutID: Int
        get() = R.layout.activity_movie_detail

    override fun initData() {
        movieDetailViewModel = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)
        movieDetailViewModel.fetchMovieDetail(297802)
        movieDetailViewModel.getMovieDetailLiveData().observe(this, Observer {
            showToast("Done")
            println(it.toString())
        })
    }


}