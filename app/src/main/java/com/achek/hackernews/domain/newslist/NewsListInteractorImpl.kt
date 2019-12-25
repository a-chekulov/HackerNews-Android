package com.achek.hackernews.domain.newslist

import com.achek.hackernews.data.common.SchedulersProvider
import com.achek.hackernews.data.newslist.NewsRepo
import com.achek.hackernews.data.newslist.model.NewsModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class NewsListInteractorImpl(
    private val repo: NewsRepo,
    private val scheduler: SchedulersProvider
) : NewsListInteractor {
    override fun getNewsById(id: Int): Single<NewsModel> {
        return repo.getNewsById(id).observeOn(scheduler.io())
    }

    override fun updateRecentNewsIds(): Completable {
        return repo.loadRecentNewsIds().observeOn(scheduler.io())
    }

    override fun loadPage(page: Int): Observable<NewsModel> {

        return Observable.create { emitter ->
            repo.getNewsIdsByPage(page)
                .observeOn(scheduler.io())
                .subscribe({
                    for (id in it) {
                        repo.getNewsById(id)
                            .subscribe({ news: NewsModel ->
                                emitter.onNext(news)
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