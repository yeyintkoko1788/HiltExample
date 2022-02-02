package com.yeyint.hiltexample.bindingadapter

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yeyint.hiltexample.BaseApplication
import com.yeyint.hiltexample.adapter.PostAdapter
import com.yeyint.hiltexample.models.post.PostDataVO
import com.yeyint.hiltexample.models.post.PostResponse
import com.yeyint.hiltexample.utils.Constant
import com.yeyint.hiltexample.utils.NetworkResult

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data : ArrayList<PostDataVO>?){
    val adapter = recyclerView.adapter as PostAdapter
    if (data != null) {
        adapter.setItem(data)
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    Glide.with(BaseApplication.getInstance())
        .load(Constant.START_IMAGE_URL+imgUrl)
        .into(imgView)
}

@BindingAdapter("postApiStatus")
fun bindStatus(progressBar : ProgressBar, data : NetworkResult<PostResponse?>?){
    when(data?.status){
        NetworkResult.Status.SUCCESS -> {
            progressBar.visibility = View.GONE
        }
        NetworkResult.Status.ERROR -> {
            progressBar.visibility = View.GONE
            Toast.makeText(BaseApplication.getInstance(),data.message,Toast.LENGTH_SHORT).show()
        }
        NetworkResult.Status.LOADING -> {
            progressBar.visibility = View.VISIBLE
        }
        else -> {}
    }
}
