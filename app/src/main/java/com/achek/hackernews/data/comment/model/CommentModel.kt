package com.achek.hackernews.data.comment.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CommentModel(
    @SerializedName("by")
    @Expose
    val by: String,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("kids")
    @Expose
    val kids: List<Int>?,
    @SerializedName("parent")
    @Expose
    val parent: Int,
    @SerializedName("text")
    @Expose
    val text: String?,
    @SerializedName("time")
    @Expose
    val time: Int,
    @SerializedName("type")
    @Expose
    val type: String
)