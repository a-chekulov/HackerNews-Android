package com.achek.hackernews.data.newslist.repository

import com.achek.hackernews.data.common.model.Item
import com.achek.hackernews.data.newslist.NewsDataSource
import com.achek.hackernews.data.newslist.NewsRepo
import io.reactivex.Completable
import io.reactivex.Single

class NewsRepository(
    private val localDataSource: NewsDataSource,
    private val remoteDataSource: NewsDataSource
) : NewsRepo {
    override fun getItemById(id: Int): Single<Item> {
        return remoteDataSource.getItemById(id)
    }

    override fun loadRecentNewsIds(): Completable {
        return remoteDataSource.getRecentNewsIds().flatMapCompletable {
            localDataSource.saveItemsIds(it)
        }
    }

    override fun loadTopNewsIds(): Completable {
       return remoteDataSource.getTopNewsIds().flatMapCompletable {
           localDataSource.saveItemsIds(it)
       }
    }

    override fun loadBestNewsIds(): Completable {
        return remoteDataSource.getBestNewsIds().flatMapCompletable {
            localDataSource.saveItemsIds(it)
        }
    }

    override fun loadAskStoriesIds(): Completable {
        return remoteDataSource.getAskStoriesIds().flatMapCompletable {
            localDataSource.saveItemsIds(it)
        }
    }

    override fun loadShowStoriesIds(): Completable {
        return remoteDataSource.getShowStoriesIds().flatMapCompletable {
            localDataSource.saveItemsIds(it)
        }
    }

    override fun loadJobStoriesIds(): Completable {
        return remoteDataSource.getJobStoriesIds().flatMapCompletable {
            localDataSource.saveItemsIds(it)
        }
    }

    override fun getItemsIdsByPage(page: Int): Single<Array<Int>> {
        return localDataSource.getItemsIdsByPage(page)
    }
}