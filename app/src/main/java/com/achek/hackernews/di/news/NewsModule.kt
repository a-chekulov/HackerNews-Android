package com.achek.hackernews.di.news

import com.achek.hackernews.data.common.SchedulersProvider
import com.achek.hackernews.data.newslist.NewsDataSource
import com.achek.hackernews.data.newslist.NewsRepo
import com.achek.hackernews.data.newslist.memory.LocalNewsDataSource
import com.achek.hackernews.data.newslist.network.NetworkNewsDataSource
import com.achek.hackernews.data.newslist.repository.NewsRepository
import com.achek.hackernews.domain.newslist.NewsListInteractor
import com.achek.hackernews.domain.newslist.NewsListInteractorImpl
import com.achek.hackernews.presentation.presenter.newslist.NewsListPresenter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Named

@Module
class NewsModule {
    @Provides
    fun provideNewsListPresenter(
        interactor: NewsListInteractor,
        provider: SchedulersProvider,
        cicerone: Cicerone<Router>
    ) : NewsListPresenter {
        return NewsListPresenter(interactor, provider, cicerone)
    }

    @Provides
    fun provideInteractor(
        repo: NewsRepo,
        scheduler: SchedulersProvider
    ): NewsListInteractor {
        return NewsListInteractorImpl(repo, scheduler)
    }

    @Provides
    @Named("local")
    fun provideLocalNewsDataSource(): NewsDataSource {
        return LocalNewsDataSource()
    }

    @Provides
    @Named("remote")
    fun provideRemoteDataSource(retrofit: Retrofit): NewsDataSource {
        return NetworkNewsDataSource(retrofit)
    }

    @Provides
    fun provideRepo(
        @Named("local") localDataSource: NewsDataSource,
        @Named("remote") remoteDataSource: NewsDataSource
    ): NewsRepo {
        return NewsRepository(localDataSource, remoteDataSource)
    }


}