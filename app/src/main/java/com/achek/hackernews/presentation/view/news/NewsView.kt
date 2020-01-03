package com.achek.hackernews.presentation.view.news

import com.achek.hackernews.data.comment.model.CommentModel
import com.achek.hackernews.data.newslist.model.NewsModel
import com.arellomobile.mvp.MvpView

interface NewsView: MvpView {
    fun showNewsInfo(newsModel: NewsModel)
    fun showMessage(text: String)
    fun addComment(commentModel: CommentModel)
}

