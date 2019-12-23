package com.achek.hackernews.presentation.presenter.newslist

import com.achek.hackernews.data.common.SchedulersProvider
import com.achek.hackernews.domain.newslist.NewsListInteractor
import com.achek.hackernews.presentation.view.newslist.NewsListView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

@InjectViewState
class NewsListPresenter(
    private val interactor: NewsListInteractor,
    private val provider: SchedulersProvider,
    private val cicerone: Cicerone<Router>
): MvpPresenter<NewsListView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }
}