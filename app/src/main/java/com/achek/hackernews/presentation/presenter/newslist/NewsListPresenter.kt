package com.achek.hackernews.presentation.presenter.newslist

import com.achek.hackernews.data.common.SchedulersProvider
import com.achek.hackernews.domain.newslist.NewsListInteractor
import com.achek.hackernews.presentation.view.news.NewsScreen
import com.achek.hackernews.presentation.view.newslist.NewsListView
import com.achek.hackernews.presentation.view.newslist.OpenModeNews
import com.achek.hackernews.utils.getOpenModNews
import com.achek.hackernews.utils.plusAssign
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.disposables.CompositeDisposable
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router


@InjectViewState
class NewsListPresenter @AssistedInject constructor(
    private val interactor: NewsListInteractor,
    private val provider: SchedulersProvider,
    private val cicerone: Cicerone<Router>
) : MvpPresenter<NewsListView>() {

    private val disposables = CompositeDisposable()
    private var page: Int = -1
    private var items: Int = 0

    fun loadNews() {
        page++
        disposables += interactor.loadPage(page)
            .observeOn(provider.ui())
            .subscribe({
                for (item in it) {
                    viewState.addNews(item)
                }
                viewState.showProgress(false)
                viewState.isRefreshing(false)
            }, {
                items++
            })
    }

    fun showNews(id: Int) {
        cicerone.router.navigateTo(NewsScreen(id))
    }

    fun refresh(pos: Int) {
        page = -1
        items = 0

        val mode = pos.getOpenModNews()
        viewState.setMode(mode)

        viewState.isRefreshing(true)
        disposables += when (mode) {
            OpenModeNews.RECENT -> interactor.updateRecentStoriesIds()
            OpenModeNews.TOP -> interactor.updateTopStoriesIds()
            OpenModeNews.BEST -> interactor.updateBestStoriesIds()
            OpenModeNews.ASK -> interactor.updateAskStoriesIds()
            OpenModeNews.JOB -> interactor.updateJobStoriesIds()
        }
            .observeOn(provider.ui())
            .subscribe({
                loadNews()
            }, { err ->
                viewState.isRefreshing(false)
                viewState.showProgress(false)
                viewState.showMessage(err.toString())
            })
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(): NewsListPresenter
    }

}