package com.hades.kotlintrainning.ui.base

import android.support.v7.app.AppCompatActivity
import android.widget.Toast

import com.hades.kotlintrainning.components.LoadingDialog

open class BaseActivity : AppCompatActivity() {

    private var mDialog: LoadingDialog? = null


    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


    fun load() {
        if (mDialog != null && mDialog!!.isShowing && !isFinishing) {
            return
        }
        mDialog = LoadingDialog(this)
        mDialog!!.show()
    }

    fun unLoad() {
        if (mDialog == null) return
        mDialog!!.dismiss()
    }

    fun showLoading(isShow: Boolean) {
        runOnUiThread(Runnable {
            if (isFinishing) return@Runnable
            if (isShow) {
                load()
            } else {
                unLoad()
            }
        })
    }
}
