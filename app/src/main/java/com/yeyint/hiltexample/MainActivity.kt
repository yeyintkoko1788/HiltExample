package com.yeyint.hiltexample

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yeyint.hiltexample.activity.BaseActivity
import com.yeyint.hiltexample.adapter.PostAdapter
import com.yeyint.hiltexample.databinding.ActivityMainBinding
import com.yeyint.hiltexample.utils.NetworkResult
import com.yeyint.hiltexample.viewmodel.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewModel by viewModels<PostViewModel>()

    companion object {
        fun getIntent() : Intent {
            return Intent(BaseApplication.getInstance(), MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpToolbar(binding.toolbar,false)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this
        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        val layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvPost.layoutManager = layoutManager
        binding.rvPost.adapter = PostAdapter()

        fetchResponse()
    }

    private fun fetchResponse(){
        viewModel.fatchPost()
    }

}