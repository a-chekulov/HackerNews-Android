package com.achek.hackernews.domain.newslist

import com.achek.hackernews.data.common.SchedulersProvider
import com.achek.hackernews.data.newslist.NewsRepo

class NewsListInteractorImpl(
    private val newslistRepo: NewsRepo,
    private val sheduler: SchedulersProvider
) : NewsListInteractor {

}