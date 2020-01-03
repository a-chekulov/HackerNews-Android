package com.achek.hackernews.presentation.presenter.news

import com.achek.hackernews.data.common.SchedulersProvider
import com.achek.hackernews.domain.news.CommentInteractor
import com.achek.hackernews.domain.newslist.NewsListInteractor
import com.achek.hackernews.presentation.view.news.NewsView
import com.achek.hackernews.utils.plusAssign
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.disposables.CompositeDisposable
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

@InjectViewState
class NewsPresenter @AssistedInject constructor(
    @Assisted private val id: Int,
    private val newsInteractor:NewsListInteractor,
    private val commentInteractor: CommentInteractor,
    private val provider: SchedulersProvider,
    private val cicerone: Cicerone<Router>
): MvpPresenter<NewsView>() {

    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        disposables += newsInteractor.getNewsById(id)
            .observeOn(provider.ui())
            .subscribe({news ->
                viewState.showMessage(news.kids?.size.toString())
                viewState.showNewsInfo(news)
                news.kids?.let { loadComment(it) }
            }, {
                viewState.showMessage(it.toString())
            })
        super.onFirstViewAttach()
    }

    private fun loadComment(commentsIds: List<Int>){
        for (commentId in commentsIds) {
            disposables += commentInteractor.getCommentById(commentId)
                .observeOn(provider.ui())
                .subscribe({
                    viewState.addComment(it)
                }, {
                    viewState.showMessage(it.toString())
                })
        }
    }

    fun back() {
        cicerone.router.exit()
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(id: Int): NewsPresenter
    }
}