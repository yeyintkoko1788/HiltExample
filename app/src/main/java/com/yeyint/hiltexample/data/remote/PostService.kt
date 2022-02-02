package com.yeyint.hiltexample.data.remote

import com.yeyint.hiltexample.models.post.PostResponse
import com.yeyint.hiltexample.utils.Constant
import retrofit2.Response
import retrofit2.http.POST

interface PostService {
    @POST(Constant.API_GET_TRENDING_POST)
    suspend fun getPostList() : Response<PostResponse>
}