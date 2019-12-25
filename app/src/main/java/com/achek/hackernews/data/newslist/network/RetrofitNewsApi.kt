package com.achek.hackernews.data.newslist.network

import com.achek.hackernews.data.newslist.model.NewsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitNewsApi {
    @GET("item/{item}.json")
    fun getNewsById(@Path("item") item: Int): Call<NewsModel>

    @GET("newstories.json")
    fun getNewsIds(): Call<Array<Int>>
}
