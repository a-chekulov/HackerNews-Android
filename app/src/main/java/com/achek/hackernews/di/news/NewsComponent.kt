package com.achek.hackernews.di.news

import com.achek.hackernews.di.AssistedInjectModule
import com.achek.hackernews.presentation.presenter.news.NewsPresenter
import com.achek.hackernews.presentation.presenter.newslist.NewsListPresenter
import dagger.Subcomponent

@Subcomponent(modules = [NewsListModule::class, NewsModule::class, AssistedInjectModule::class])
interface NewsComponent {
    val newsListPresenterFactory: NewsListPresenter.Factory
    val newsPresenterFactory: NewsPresenter.Factory
}