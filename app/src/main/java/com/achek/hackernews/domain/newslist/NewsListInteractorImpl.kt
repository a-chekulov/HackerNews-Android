package com.achek.hackernews.domain.newslist

import com.achek.hackernews.data.common.SchedulersProvider
import com.achek.hackernews.data.common.model.Item
import com.achek.hackernews.data.newslist.NewsRepo
import io.reactivex.Completable
import io.reactivex.Single

class NewsListInteractorImpl(
    private val repo: NewsRepo,
    private val scheduler: SchedulersProvider
) : NewsListInteractor {

    override fun getItemById(id: Int): Single<Item> {
        return repo.getItemById(id).observeOn(scheduler.io())
    }

    override fun updateRecentStoriesIds(): Completable {
        return repo.loadRecentNewsIds().observeOn(scheduler.io())
    }

    override fun updateTopStoriesIds(): Completable {
        return repo.loadTopNewsIds().observeOn(scheduler.io())
    }

    override fun updateBestStoriesIds(): Completable {
        return repo.loadBestNewsIds().observeOn(scheduler.io())
    }

    override fun updateAskStoriesIds(): Completable {
        return repo.loadAskStoriesIds().observeOn(scheduler.io())
    }

    override fun updateJobStoriesIds(): Completable {
        return repo.loadJobStoriesIds().observeOn(scheduler.io())
    }

    override fun loadPage(page: Int): Single<List<Item>> {

        val list = mutableListOf<Item>()

        return Single.create { emitter ->
            repo.getItemsIdsByPage(page)
                .observeOn(scheduler.io())
                .subscribe({
                    for (id in it) {
                        repo.getItemById(id)
                            .subscribe({ item: Item ->
                                list.add(item)
                                if (list.size == NewsRepo.PAGE_SIZE) {
                                    emitter.onSuccess(list.toList())
                                }
                            }, { err ->
                                emitter.tryOnError(err)
                            })
                    }
                }, { err ->
                    emitter.tryOnError(err)
                })
        }
    }
}