package com.yeyint.hiltexample.models.post

import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("status")
    val status : String,
    @SerializedName("data")
    val data : ArrayList<PostDataVO>,
    @SerializedName("message")
    val message : String
)

data class PostDataVO(
    @SerializedName("post_id")
    val postId : Int,
    @SerializedName("category_id")
    val categoryID : Int,
    @SerializedName("category_name")
    val categoryName : String,
    @SerializedName("title")
    val title : String,
    @SerializedName("image")
    val image : String,
    @SerializedName("description")
    val description : String,
    @SerializedName("register_date")
    val registerDate : String,
    @SerializedName("view_count")
    val viewCount : Int
)