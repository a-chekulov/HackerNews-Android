package com.achek.hackernews.presentation.view.newslist

import ru.terrakok.cicerone.android.support.SupportAppScreen

class NewsListScreen : SupportAppScreen() {
    override fun getFragment(): NewsListFragment = NewsListFragment()
}