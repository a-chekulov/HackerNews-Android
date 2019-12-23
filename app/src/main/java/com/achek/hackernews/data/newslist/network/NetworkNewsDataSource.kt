package com.achek.hackernews.data.newslist.network

import com.achek.hackernews.data.newslist.NewsDataSource
import retrofit2.Retrofit

class NetworkNewsDataSource(private val retrofit: Retrofit): NewsDataSource{
}