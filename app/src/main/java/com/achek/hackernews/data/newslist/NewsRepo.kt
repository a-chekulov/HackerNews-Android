package com.achek.hackernews.data.newslist

import com.achek.hackernews.data.newslist.model.NewsModel
import io.reactivex.Completable
import io.reactivex.Single

interface NewsRepo {
    fun getNewsById(id: Int): Single<NewsModel>
    fun loadRecentNewsIds(): Completable
    fun getNewsIdsByPage(page: Int): Single<Array<Int>>


    companion object {
        const val PAGE_SIZE: Int = 20
    }
}