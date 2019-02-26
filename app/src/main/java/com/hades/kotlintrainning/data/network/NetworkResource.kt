package com.hades.kotlintrainning.data.network

import android.arch.lifecycle.LiveData

data class NetworkResource<T>(
    val data: T,
    val networkState: LiveData<NetworkState>? = null,
    val refreshState: LiveData<NetworkState>? = null,
    val refresh: (() -> Unit)? = null,
    val retry: (() -> Unit)? = null,
    val clear: (() -> Unit)? = null
)