package com.achek.hackernews.di.common

import com.achek.hackernews.di.news.NewsComponent
import com.achek.hackernews.di.splash.SplashComponent
import com.achek.hackernews.presentation.view.MainActivity
import com.achek.hackernews.presentation.view.NewsApp
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent{
    fun inject(app: NewsApp)
    fun inject(activity: MainActivity)
    fun plusNewsComponent(): NewsComponent
    fun plusSplashComponent(): SplashComponent
}

