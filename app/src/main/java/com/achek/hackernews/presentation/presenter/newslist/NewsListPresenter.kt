package com.achek.hackernews.presentation.presenter.newslist

import com.achek.hackernews.data.common.SchedulersProvider
import com.achek.hackernews.domain.newslist.NewsListInteractor
import com.achek.hackernews.presentation.view.news.NewsScreen
import com.achek.hackernews.presentation.view.newslist.NewsListView
import com.achek.hackernews.utils.plusAssign
import com.achek.hackernews.utils.toStory
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

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        disposables += interactor.updateRecentStoriesIds()
            .observeOn(provider.ui())
            .subscribe({
                loadNews()
            }, { err ->
                viewState.showMessage(err.toString())
            })
    }

    fun loadNews() {
        page++
        disposables += interactor.loadPage(page)
            .observeOn(provider.ui())
            .subscribe({
                for (item in it) {
                    if (item.type == "story") viewState.addNews(item.toStory())
                }
                viewState.showProgress(false)
            }, {
                items++
            })
    }

    fun showNews(id: Int) {
        cicerone.router.navigateTo(NewsScreen(id))
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(): NewsListPresenter
    }

}