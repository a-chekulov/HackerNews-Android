package com.achek.hackernews.presentation.view.newslist

import ru.terrakok.cicerone.android.support.SupportAppScreen

enum class OpenModeNews {
    RECENT,
    TOP,
    BEST,
    ASK,
    JOB
}

class NewsListScreen : SupportAppScreen() {
    override fun getFragment(): NewsListFragment = NewsListFragment()
}