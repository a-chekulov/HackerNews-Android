package com.achek.hackernews.data.comment

import com.achek.hackernews.data.comment.model.CommentModel
import io.reactivex.Single

interface CommentRepo {
    fun getCommentById(id: Int): Single<CommentModel>
}