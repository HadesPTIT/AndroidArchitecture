package com.hades.kotlintrainning.utils

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

@BindingAdapter("android:src")
fun setImageViewResource(imageView: ImageView, url: String) {
    Glide.with(imageView).load("http://image.tmdb.org/t/p/original/$url").into(imageView)
}