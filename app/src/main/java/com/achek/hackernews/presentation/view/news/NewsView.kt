package com.achek.hackernews.presentation.view.news

import com.achek.hackernews.data.comment.model.CommentModel
import com.achek.hackernews.data.common.model.Item
import com.arellomobile.mvp.MvpView

interface NewsView: MvpView {
    fun showNewsInfo(item: Item)
    fun showMessage(text: String)
    fun addComment(commentModel: CommentModel)
}

