package com.hades.kotlintrainning.ui.moviedetail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.Paint
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.hades.kotlintrainning.R
import com.hades.kotlintrainning.common.AppConstant
import com.hades.kotlintrainning.data.entity.*
import com.hades.kotlintrainning.ui.base.BaseDataBindingActivity
import com.hades.kotlintrainning.databinding.ActivityMovieDetailBinding
import com.hades.kotlintrainning.ui.base.BaseViewAdapter
import com.hades.kotlintrainning.ui.base.SingleTypeAdapter
import com.hades.kotlintrainning.viewmodel.MovieDetailViewModel
import com.hades.kotlintrainning.ui.youtube.YoutubePlayerActivity
import java.util.Arrays

class MovieDetailActivity : BaseDataBindingActivity<ActivityMovieDetailBinding>() {

    private lateinit var movieDetailViewModel: MovieDetailViewModel

    private lateinit var crewAdapter: SingleTypeAdapter<Crew>
    private lateinit var castAdapter: SingleTypeAdapter<Cast>
    private lateinit var similarMovieAdapter: SingleTypeAdapter<MovieDetail>
    private lateinit var videoAdapter : SingleTypeAdapter<Video>

    override val layoutID: Int
        get() = R.layout.activity_movie_detail

    override fun initData() {
        movieDetailViewModel = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)

        val movie = intent.getParcelableExtra<Movie>(AppConstant.INTENT_MOVIE)
        Glide.with(this).load(String.format(AppConstant.IMAGE_URL, movie.poster_path)).into(mBinding.image)
        mBinding.expandButton.paintFlags = mBinding.expandButton.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        movieDetailViewModel.fetchMovieDetail(movie.id.toLong())

        initEvent()
    }

    private fun initEvent() {

        movieDetailViewModel.loadingLiveData.observe(this, Observer {
            it?.let {
                showLoading(it)
            }
        })

        movieDetailViewModel.getMovieDetailLiveData().observe(this, Observer { movieDetail ->

            updateMovieDetail(movieDetail!!)

            movieDetail.videos?.let {
                updateMovieVideo(movieDetail.videos!!)
            }
            movieDetail.casts?.let {
                updateMovieCast(movieDetail.casts!!)
            }
            movieDetail.crews?.let {
                updateMovieCrew(movieDetail.crews!!)
            }
            movieDetail.similarMovies?.let {
                updateSimilarMovie(movieDetail.similarMovies!!)
            }
        })

    }

    private fun updateMovieDetail(movieDetail: MovieDetail) {
        mBinding.movieTitle.text = movieDetail.header
        mBinding.movieDesc.text = movieDetail.description
        if (movieDetail.status != null) mBinding.movieStatus.items = Arrays.asList(movieDetail.status)
        mBinding.collectionItemPicker.isUseRandomColor = true
        if (movieDetail.genres != null) mBinding.collectionItemPicker.items = getGenres(movieDetail.genres!!)
        mBinding.txtRuntime.text = movieDetail.runTime.toString()
    }

    private fun updateMovieVideo(videos: List<Video>) {
        mBinding.recyclerView.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,false)
        videoAdapter = SingleTypeAdapter(this,R.layout.item_video)
        videoAdapter.set(videos)
        mBinding.recyclerView.adapter = videoAdapter
        mBinding.recyclerView.smoothScrollToPosition(1)
    }

    private fun updateMovieCast(casts: List<Cast>) {
        mBinding.includedLayout.castList.layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        mBinding.includedLayout.castList.visibility = View.VISIBLE
        mBinding.includedLayout.titleCast.visibility = View.VISIBLE
        castAdapter = SingleTypeAdapter(this,R.layout.item_cast)
        castAdapter.set(casts)
        mBinding.includedLayout.castList.adapter = castAdapter
    }

    private fun updateMovieCrew(crews: List<Crew>) {
        mBinding.includedLayout.crewList.layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        mBinding.includedLayout.crewList.visibility = View.VISIBLE
        crewAdapter = SingleTypeAdapter(this, R.layout.item_credit)
        crewAdapter.set(crews)
        mBinding.includedLayout.crewList.adapter = crewAdapter
    }

    private fun updateSimilarMovie(similarMovies: List<MovieDetail>) {
        mBinding.includedSimilarLayout.movieSimilarTitle.visibility = View.VISIBLE
        mBinding.includedSimilarLayout.moviesList.layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        mBinding.includedSimilarLayout.moviesList.visibility = View.VISIBLE
        similarMovieAdapter = SingleTypeAdapter(this, R.layout.item_similar_movie)
        similarMovieAdapter.set(similarMovies)
        mBinding.includedSimilarLayout.moviesList.adapter = similarMovieAdapter
    }

    private fun getGenres(genres: List<Genre>): MutableList<String> {
        val genresName = mutableListOf<String>()
        for (genre in genres) {
            genresName.add(genre.name)
        }
        return genresName
    }

    fun handleExpandAction(view : View) {
        if (mBinding.includedLayout.expandableLayout.isExpanded) {
            mBinding.expandButton.text = getString(R.string.read_more)
            mBinding.includedLayout.expandableLayout.collapse()
        } else {
            mBinding.expandButton.text = getString(R.string.read_less)
            mBinding.includedLayout.expandableLayout.expand()
        }
    }

    fun navigateVideo(view : View) {
        val intent = Intent(this@MovieDetailActivity,YoutubePlayerActivity::class.java)
        startActivity(intent)
    }

    inner class ItemClickListener : BaseViewAdapter.Presenter {

        fun onItemClick(video : Video) {
            println(video.id)
            startActivity(Intent(this@MovieDetailActivity,YoutubePlayerActivity::class.java))
        }

        fun navigateVideo(video : Video) {
            Log.e("tag",video.key + "dfsfdsf")
        }

    }

}