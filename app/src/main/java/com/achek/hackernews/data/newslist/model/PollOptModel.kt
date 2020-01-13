package com.achek.hackernews.data.newslist.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PollOptModel(
    @SerializedName("by")
    @Expose
    val by: String,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("poll")
    @Expose
    val poll: Int,
    @SerializedName("score")
    @Expose
    val score: Int,
    @SerializedName("text")
    @Expose
    val text: String,
    @SerializedName("time")
    @Expose
    val time: Int,
    @SerializedName("type")
    @Expose
    val type: String
)