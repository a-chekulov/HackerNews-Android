package com.achek.hackernews.data.newslist

import com.achek.hackernews.data.common.model.Item
import io.reactivex.Completable
import io.reactivex.Single

interface NewsDataSource {
    fun getItemById(id: Int): Single<Item>

    fun getRecentNewsIds(): Single<Array<Int>>
    fun getTopNewsIds(): Single<Array<Int>>
    fun getBestNewsIds(): Single<Array<Int>>
    fun getAskStoriesIds(): Single<Array<Int>>
    fun getShowStoriesIds(): Single<Array<Int>>
    fun getJobStoriesIds(): Single<Array<Int>>

    fun saveItemsIds(arr: Array<Int>): Completable
    fun getItemsIdsByPage(page: Int): Single<Array<Int>>
}