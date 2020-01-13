package com.achek.hackernews.data.newslist.memory

import com.achek.hackernews.data.common.model.Item
import com.achek.hackernews.data.newslist.NewsDataSource
import com.achek.hackernews.data.newslist.NewsRepo
import io.reactivex.Completable
import io.reactivex.Single

class LocalNewsDataSource : NewsDataSource {

    private var itemIds: Array<Int>? = null

    override fun saveItemsIds(arr: Array<Int>): Completable {
        return Completable.fromAction {
            this.itemIds = arr
            return@fromAction
        }
    }

    override fun getItemsIdsByPage(page: Int): Single<Array<Int>> {
        return Single.create { emitter ->
            itemIds?.let {
                val newsIdList = itemIds!!.toMutableList().filterIndexed { index, _ ->
                     index >= (page * NewsRepo.PAGE_SIZE) && index < ((page + 1) * NewsRepo.PAGE_SIZE)
                }
                emitter.onSuccess(newsIdList.toTypedArray())
            } ?: emitter.onError(java.lang.NullPointerException())
        }
    }

    override fun getItemById(id: Int): Single<Item> {
        throw NotImplementedError()
    }

    override fun getRecentNewsIds(): Single<Array<Int>> {
        throw NotImplementedError()
    }

    override fun getTopNewsIds(): Single<Array<Int>> {
        throw NotImplementedError()
    }

    override fun getBestNewsIds(): Single<Array<Int>> {
        throw NotImplementedError()
    }

    override fun getAskStoriesIds(): Single<Array<Int>> {
        throw NotImplementedError()
    }

    override fun getShowStoriesIds(): Single<Array<Int>> {
        throw NotImplementedError()
    }

    override fun getJobStoriesIds(): Single<Array<Int>> {
        throw NotImplementedError()
    }

}