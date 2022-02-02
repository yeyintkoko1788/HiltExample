package com.yeyint.hiltexample.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers


// Only Network Access
fun <A> performNetworkOperation( networkCall: suspend () -> NetworkResult<A>): LiveData<NetworkResult<A?>> =
    liveData(Dispatchers.IO) {
        emit(NetworkResult.loading())

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == NetworkResult.Status.SUCCESS) {
            emit(NetworkResult.success(responseStatus.data))
        } else if (responseStatus.status == NetworkResult.Status.ERROR) {
            emit(NetworkResult.error(responseStatus.message))
        }
    }