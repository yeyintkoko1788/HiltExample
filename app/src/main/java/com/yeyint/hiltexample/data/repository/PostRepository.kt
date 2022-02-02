package com.yeyint.hiltexample.data.repository

import com.yeyint.hiltexample.data.remote.BaseDataSource
import com.yeyint.hiltexample.data.remote.PostRemoteDataSource
import com.yeyint.hiltexample.models.post.PostResponse
import com.yeyint.hiltexample.utils.NetworkResult
import com.yeyint.hiltexample.utils.performNetworkOperation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val postRemoteDataSource: PostRemoteDataSource
) :BaseDataSource(){
        /*fun getPostList() = performNetworkOperation {
            postRemoteDataSource.getPost()
        }*/
        suspend fun getPostList(): Flow<NetworkResult<PostResponse>> {
            return flow {
                emit(NetworkResult.loading())
                emit(getResult { postRemoteDataSource.getPost() })
            }.flowOn(Dispatchers.IO)
        }
}