package com.achek.hackernews.data.comment.network

import com.achek.hackernews.data.comment.model.CommentModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitCommentApi {
    @GET("item/{item}.json")
    fun getCommentById(@Path("item") item: Int): Call<CommentModel>
}