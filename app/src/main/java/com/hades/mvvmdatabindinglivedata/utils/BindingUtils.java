package com.hades.mvvmdatabindinglivedata.utils;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Hades on 9/10/2018.
 */
public class BindingUtils {

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView view, int resource) {
        Glide.with(view.getContext())
                .load(resource)
                .into(view);
    }

    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }


}
