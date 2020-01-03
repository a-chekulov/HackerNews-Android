package com.achek.hackernews.data.comment.repository

import com.achek.hackernews.data.comment.CommentDataSource
import com.achek.hackernews.data.comment.CommentRepo
import com.achek.hackernews.data.comment.model.CommentModel
import io.reactivex.Single

class CommentRepository(
    private val localCommentDataSource: CommentDataSource,
    private val removeCommentDataSource: CommentDataSource
): CommentRepo {

    override fun getCommentById(id: Int): Single<CommentModel> {
        return removeCommentDataSource.getCommentById(id)
    }
}