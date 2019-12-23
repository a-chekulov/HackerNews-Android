package com.achek.hackernews.data.newslist.repository

import com.achek.hackernews.data.newslist.NewsDataSource
import com.achek.hackernews.data.newslist.NewsRepo

class NewsRepository(
    private val localDataSource: NewsDataSource,
    private val remoteDataSource: NewsDataSource
): NewsRepo {
}