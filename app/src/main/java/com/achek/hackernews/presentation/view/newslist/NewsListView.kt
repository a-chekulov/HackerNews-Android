package com.achek.hackernews.presentation.view.newslist

import com.achek.hackernews.data.common.model.Item
import com.arellomobile.mvp.MvpView

interface NewsListView: MvpView {
    fun showMessage(text: String)
    fun showContent(list: List<Item>)
    fun addNews(item: Item)
    fun showProgress(showing: Boolean)
    fun isRefreshing(show: Boolean)
    fun setMode(openModeNews: OpenModeNews)
}