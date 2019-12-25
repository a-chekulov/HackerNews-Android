package com.achek.hackernews.data.newslist

import com.achek.hackernews.data.newslist.model.NewsModel
import io.reactivex.Completable
import io.reactivex.Single

interface NewsDataSource {
    fun getNewsById(id: Int): Single<NewsModel>
    fun getRecentNewsIds(): Single<Array<Int>>

    fun saveNewsIds(arr: Array<Int>): Completable
    fun getNewsIdsByPage(page: Int): Single<Array<Int>>
}