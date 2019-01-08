package com.hades.kotlintrainning.utils

import android.databinding.BindingAdapter
import android.support.v7.widget.AppCompatImageView
import android.widget.ImageView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter(value = ["app:url","app:quality"],requireAll = false)
fun setImageViewResource(imageView: ImageView, url: String?,quality : String? = "original") {
    Glide.with(imageView).load("http://image.tmdb.org/t/p/$quality/$url").into(imageView)
}

@BindingAdapter(value = ["app:url","app:quality"],requireAll = false)
fun setImageViewResource(imageView: CircleImageView, url: String?,quality : String? = "original") {
    Glide.with(imageView).load("http://image.tmdb.org/t/p/$quality/$url").into(imageView)
}

@BindingAdapter(value = ["app:thumbnailKey","app:quality"], requireAll = false)
fun setYoutubeImageResource(imageView : AppCompatImageView, key : String?, quality: String? = "original") {
    Glide.with(imageView).load("https://img.youtube.com/vi/$key/$quality.jpg").into(imageView)
}