package com.achek.hackernews.domain.newslist

import com.achek.hackernews.data.newslist.model.NewsModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface NewsListInteractor {
    fun getNewsById(id: Int): Single<NewsModel>
    fun updateRecentNewsIds(): Completable
//    fun loadPage(page: Int): Single<List<NewsModel>>
    fun loadPage(page: Int):Observable<NewsModel>
}
