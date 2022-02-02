package com.yeyint.hiltexample.data.remote

import com.yeyint.hiltexample.utils.NetworkResult
import retrofit2.Response


abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return NetworkResult.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): NetworkResult<T> {
        return NetworkResult.error("Api call failed : $message")
    }


}