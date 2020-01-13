package com.achek.hackernews.domain.newslist

import com.achek.hackernews.data.common.model.Item
import io.reactivex.Completable
import io.reactivex.Single

interface NewsListInteractor {
    fun getItemById(id: Int): Single<Item>
    fun updateRecentStoriesIds(): Completable
    fun updateTopStoriesIds(): Completable
    fun updateBestStoriesIds(): Completable
    fun updateAskStoriesIds(): Completable
    fun updateJobStoriesIds(): Completable

    fun loadPage(page: Int): Single<List<Item>>
}
