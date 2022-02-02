package com.yeyint.hiltexample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yeyint.hiltexample.databinding.ViewItemPostBinding
import com.yeyint.hiltexample.models.post.PostDataVO

class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>(){

    private var item = ArrayList<PostDataVO>()

    fun setItem(item : ArrayList<PostDataVO>){
        this.item.clear()
        this.item = item
        notifyDataSetChanged()
    }

    class PostViewHolder(private val itemBinding: ViewItemPostBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(post : PostDataVO){
            itemBinding.post = post
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            itemBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(ViewItemPostBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(item[position])
    }

    override fun getItemCount(): Int = item.size
}