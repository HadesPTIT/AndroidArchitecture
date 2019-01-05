package com.hades.kotlintrainning.utils

import android.app.Activity
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import com.hades.kotlintrainning.constant.AppConstant
import com.hades.kotlintrainning.constant.AppConstant.Companion.INTENT_MOVIE
import com.hades.kotlintrainning.data.entity.Movie
import com.hades.kotlintrainning.moviedetail.MovieDetailActivity

class Navigation : AppConstant {

    companion object {

        fun navigateToScreen(activity: Activity, movie: Movie, options: ActivityOptionsCompat?) {
            val intent = Intent(activity,MovieDetailActivity::class.java)
            intent.putExtra(INTENT_MOVIE,movie)
            ActivityCompat.startActivity(activity,intent, options?.toBundle())
        }

    }

}