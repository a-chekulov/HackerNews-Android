package com.achek.hackernews.domain.news

import com.achek.hackernews.data.comment.CommentRepo
import com.achek.hackernews.data.comment.model.CommentModel
import com.achek.hackernews.data.common.SchedulersProvider
import io.reactivex.Single

class CommentInteractorImpl(
    private val repo: CommentRepo,
    private val scheduler: SchedulersProvider
) : CommentInteractor {
    override fun getCommentById(id: Int): Single<CommentModel> {
        return repo.getCommentById(id).observeOn(scheduler.io())
    }
}
