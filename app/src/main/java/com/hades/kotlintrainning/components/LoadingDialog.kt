package com.hades.kotlintrainning.components

import android.app.Dialog
import android.content.Context
import android.view.ViewGroup
import android.widget.ProgressBar
import com.hades.kotlintrainning.R

class LoadingDialog(context: Context) : Dialog(context, R.style.LoadingDialog) {

    init {
        val progressBar = ProgressBar(context)
        val width = context.resources.getDimension(R.dimen.loading_dialog_width).toInt()
        val height = context.resources.getDimension(R.dimen.loading_dialog_height).toInt()
        val padding = context.resources.getDimensionPixelSize(R.dimen.loading_dialog_padding_size)
        val params = ViewGroup.LayoutParams(width,height)
        progressBar.setPadding(padding,padding,padding,padding)
        progressBar.layoutParams = params
        progressBar.isIndeterminate = true
        this.setContentView(progressBar)
        this.setCancelable(true)
        this.setCanceledOnTouchOutside(false)
    }

}