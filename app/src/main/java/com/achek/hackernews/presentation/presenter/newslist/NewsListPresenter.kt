package com.achek.hackernews.presentation.presenter.newslist

import com.achek.hackernews.data.common.SchedulersProvider
import com.achek.hackernews.domain.newslist.NewsListInteractor
import com.achek.hackernews.presentation.view.newslist.NewsListView
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

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        disposables += interactor.updateRecentNewsIds()
            .observeOn(provider.ui())
            .subscribe({
                loadNews()
            },{err ->
                viewState.showMessage(err.toString())
            })

    }

    fun loadNews(){
        page++
        disposables += interactor.loadPage(page)
            .observeOn(provider.ui())
            .subscribe({
                viewState.addNews(it)
            }, {
                viewState.showMessage(it.toString())
            })
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(): NewsListPresenter
    }

}