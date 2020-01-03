package com.achek.hackernews.di.news

import com.achek.hackernews.data.comment.CommentDataSource
import com.achek.hackernews.data.comment.CommentRepo
import com.achek.hackernews.data.comment.memory.LocalCommentDataSource
import com.achek.hackernews.data.comment.network.NetworkCommentDataSource
import com.achek.hackernews.data.comment.repository.CommentRepository
import com.achek.hackernews.data.common.SchedulersProvider
import com.achek.hackernews.domain.news.CommentInteractor
import com.achek.hackernews.domain.news.CommentInteractorImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class NewsModule {

    @Provides
    fun provideNewsInteractor(
        repo: CommentRepo,
        scheduler: SchedulersProvider
    ): CommentInteractor {
        return  CommentInteractorImpl(repo, scheduler)
    }

    @Provides
    @Named("local")
    fun provideLocalCommentDataSource(): CommentDataSource {
        return LocalCommentDataSource()
    }

    @Provides
    @Named("remote")
    fun provideRemoteDataSource(retrofit: Retrofit): CommentDataSource {
        return NetworkCommentDataSource(retrofit)
    }

    @Provides
    fun provideRepo(
        @Named("local") localDataSource: CommentDataSource,
        @Named("remote") remoteDataSource: CommentDataSource
    ): CommentRepo {
        return CommentRepository(localDataSource, remoteDataSource)
    }

}