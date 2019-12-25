package com.achek.hackernews.data.newslist.repository

import com.achek.hackernews.data.newslist.NewsDataSource
import com.achek.hackernews.data.newslist.NewsRepo
import com.achek.hackernews.data.newslist.model.NewsModel
import io.reactivex.Completable
import io.reactivex.Single

class NewsRepository(
    private val localDataSource: NewsDataSource,
    private val remoteDataSource: NewsDataSource
) : NewsRepo {
    override fun getNewsById(id: Int): Single<NewsModel> {
        return remoteDataSource.getNewsById(id)
    }

    override fun loadRecentNewsIds(): Completable {
        return remoteDataSource.getRecentNewsIds().flatMapCompletable {
            localDataSource.saveNewsIds(it)
        }
    }

    override fun getNewsIdsByPage(page: Int): Single<Array<Int>> {
        return localDataSource.getNewsIdsByPage(page)
    }
}