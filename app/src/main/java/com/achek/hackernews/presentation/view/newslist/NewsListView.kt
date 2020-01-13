package com.achek.hackernews.presentation.view.newslist

import com.achek.hackernews.data.newslist.model.NewsModel
import com.arellomobile.mvp.MvpView

interface NewsListView: MvpView {
    fun showMessage(text: String)
    fun showContent(list: List<NewsModel>)
    fun addNews(newsModel: NewsModel)
    fun showProgress(showing: Boolean)
    fun isRefreshing(show: Boolean)
}