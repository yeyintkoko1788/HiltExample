package com.yeyint.hiltexample.data.remote

import javax.inject.Inject

class PostRemoteDataSource @Inject constructor(
    private val postService: PostService
) : BaseDataSource() {
    suspend fun getPost() =
        postService.getPostList()
}