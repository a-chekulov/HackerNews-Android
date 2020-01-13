package com.achek.hackernews.data.newslist

import com.achek.hackernews.data.common.model.Item
import io.reactivex.Completable
import io.reactivex.Single

interface NewsRepo {
    fun getItemById(id: Int): Single<Item>
    fun loadRecentNewsIds(): Completable
    fun loadTopNewsIds(): Completable
    fun loadBestNewsIds(): Completable
    fun loadAskStoriesIds(): Completable
    fun loadShowStoriesIds(): Completable
    fun loadJobStoriesIds(): Completable

    fun getItemsIdsByPage(page: Int): Single<Array<Int>>


    companion object {
        const val PAGE_SIZE: Int = 20
    }
}