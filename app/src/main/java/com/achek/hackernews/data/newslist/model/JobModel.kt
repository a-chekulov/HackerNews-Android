package com.achek.hackernews.data.newslist.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class JobModel(
    @SerializedName("by")
    @Expose
    val by: String,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("score")
    @Expose
    val score: Int,
    @SerializedName("text")
    @Expose
    val text: String,
    @SerializedName("time")
    @Expose
    val time: Int,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("type")
    @Expose
    val type: String,
    @SerializedName("url")
    @Expose
    val url: String
)