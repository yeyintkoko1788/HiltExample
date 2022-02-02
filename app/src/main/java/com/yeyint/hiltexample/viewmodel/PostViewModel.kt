package com.yeyint.hiltexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yeyint.hiltexample.data.repository.PostRepository
import com.yeyint.hiltexample.models.post.PostResponse
import com.yeyint.hiltexample.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {
    private val _postResponse : MutableLiveData<NetworkResult<PostResponse?>?> = MutableLiveData()
    val postResponse : MutableLiveData<NetworkResult<PostResponse?>?> = _postResponse

    fun fatchPost() = viewModelScope.launch {
        postRepository.getPostList().collect {
            _postResponse.value = it
        }
    }

}