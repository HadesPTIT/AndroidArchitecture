package com.hades.kotlintrainning.data

import android.arch.lifecycle.LiveData
import com.hades.kotlintrainning.data.api.NetworkState

data class NetworkResource<T>(
    val data: T,
    val networkState: LiveData<NetworkState>? = null,
    val refreshState: LiveData<NetworkState>? = null,
    val refresh: (() -> Unit)? = null,
    val retry: (() -> Unit)? = null,
    val clear: (() -> Unit)? = null
)