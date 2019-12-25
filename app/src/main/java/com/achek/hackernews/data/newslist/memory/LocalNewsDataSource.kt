package com.achek.hackernews.data.newslist.memory

import com.achek.hackernews.data.newslist.NewsDataSource
import com.achek.hackernews.data.newslist.NewsRepo
import com.achek.hackernews.data.newslist.model.NewsModel
import io.reactivex.Completable
import io.reactivex.Single

class LocalNewsDataSource : NewsDataSource {

    private var newsIds: Array<Int>? = null

    override fun saveNewsIds(arr: Array<Int>): Completable {
        return Completable.fromAction {
            this.newsIds = arr
            return@fromAction
        }
    }

    override fun getNewsIdsByPage(page: Int): Single<Array<Int>> {
        return Single.create { emitter ->
            newsIds?.let {
                val newsIdList = newsIds!!.toMutableList().filterIndexed { index, i ->
                     index >= (page * NewsRepo.PAGE_SIZE) && index < ((page + 1) * NewsRepo.PAGE_SIZE)
                }
                emitter.onSuccess(newsIdList.toTypedArray())
            } ?: emitter.onError(java.lang.NullPointerException())
        }
    }

    override fun getNewsById(id: Int): Single<NewsModel> {
        throw NotImplementedError()
    }

    override fun getRecentNewsIds(): Single<Array<Int>> {
        throw NotImplementedError()
    }

}