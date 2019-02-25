package com.hades.kotlintrainning.ui.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    val loadingLiveData = MutableLiveData<Boolean>()

    val disposables = CompositeDisposable()

    fun subscribe(disposable: Disposable): Disposable {
        disposables.add(disposable)
        return disposable
    }

    /**
     * This method will be called when this viewModel is no longer used and will be destroyed
     */
    override fun onCleared() {
        super.onCleared()
        /**
         * Stop listening or clear disposable.. .
         */
        if (!disposables.isDisposed) {
            disposables.clear()
        }

    }
}
