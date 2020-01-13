package com.achek.hackernews.data.common.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("deleted")
    @Expose
    val deleted: Boolean?,
    @SerializedName("type")
    @Expose
    val type: String,
    @SerializedName("by")
    @Expose
    val by: String,
    @SerializedName("time")
    @Expose
    val time: Int,
    @SerializedName("text")
    @Expose
    val text: String?,
    @SerializedName("dead")
    @Expose
    val dead: Boolean?,
    @SerializedName("parent")
    @Expose
    val parent: Int?,
    @SerializedName("poll")
    @Expose
    val poll: Int?,
    @SerializedName("kids")
    @Expose
    val kids: List<Int>?,
    @SerializedName("url")
    @Expose
    val url: String?,
    @SerializedName("score")
    @Expose
    val score: Int?,
    @SerializedName("title")
    @Expose
    val title: String?,
    @SerializedName("parts")
    @Expose
    val parts: List<Int>?,
    @SerializedName("descendants")
    @Expose
    val descendants: Int?
)