package com.achek.hackernews.di.news

import com.achek.hackernews.presentation.view.newslist.NewsListFragment
import dagger.Subcomponent

@Subcomponent(modules = [NewsModule::class])
interface NewsComponent {
    fun inject(fragment: NewsListFragment)
}