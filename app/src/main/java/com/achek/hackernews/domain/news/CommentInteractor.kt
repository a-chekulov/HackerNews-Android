package com.achek.hackernews.domain.news

import com.achek.hackernews.data.comment.model.CommentModel
import io.reactivex.Single

interface CommentInteractor {
    fun getCommentById(id: Int): Single<CommentModel>
}