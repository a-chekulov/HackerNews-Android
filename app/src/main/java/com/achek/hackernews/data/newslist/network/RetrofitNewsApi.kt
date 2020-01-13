package com.achek.hackernews.data.newslist.network

import com.achek.hackernews.data.common.model.Item
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitNewsApi {
    @GET("newstories.json")
    fun getRecentStoriesIds(): Call<Array<Int>>

    @GET("topstories.json")
    fun getTopStoriesIds(): Call<Array<Int>>

    @GET("beststories.json")
    fun getBestStoriesIds(): Call<Array<Int>>

    @GET("askstories.json")
    fun getAskStoriesIds(): Call<Array<Int>>

    @GET("showstories.json")
    fun getShowStoriesIds(): Call<Array<Int>>

    @GET("jobstories.json")
    fun getJobStoriesIds(): Call<Array<Int>>

    @GET("item/{item}.json")
    fun getItemById(@Path("item") item: Int): Call<Item>
}
