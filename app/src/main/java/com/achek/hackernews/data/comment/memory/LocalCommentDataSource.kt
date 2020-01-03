package com.achek.hackernews.data.comment.memory

import com.achek.hackernews.data.comment.CommentDataSource
import com.achek.hackernews.data.comment.model.CommentModel
import io.reactivex.Single

class LocalCommentDataSource: CommentDataSource  {
    override fun getCommentById(id: Int): Single<CommentModel> {
        throw NotImplementedError()
    }
}