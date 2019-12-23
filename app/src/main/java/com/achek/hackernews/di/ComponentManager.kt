package com.achek.hackernews.di

import com.achek.hackernews.BuildConfig
import com.achek.hackernews.di.common.AppComponent
import com.achek.hackernews.di.common.AppModule
import com.achek.hackernews.di.common.DaggerAppComponent
import com.achek.hackernews.di.common.NetworkModule
import com.achek.hackernews.di.news.NewsComponent
import com.achek.hackernews.di.splash.SplashComponent
import com.achek.hackernews.presentation.view.NewsApp

class ComponentManager(app: NewsApp){
    val appComponent = buildAppComponent(app)
    private var newsComponent: NewsComponent? = null
    private var splashComponent: SplashComponent? = null

    private fun buildAppComponent(app: NewsApp): AppComponent {
        return DaggerAppComponent.builder()
            .appModule(AppModule(app.applicationContext))
            .networkModule(NetworkModule(BuildConfig.BASE_URL))
            .build()
    }

    fun buildSplashComponent(): SplashComponent {
        return splashComponent
            ?: appComponent.plusSplashComponent().also {
                splashComponent = it
            }
    }

    fun destroySplashScreenComponent() {
        splashComponent = null
    }

    fun buildNewsComponent(): NewsComponent {
        return newsComponent
            ?: appComponent.plusNewsComponent().also {
                newsComponent = it
            }
    }

    fun destroyNewsScreenComponent() {
        newsComponent = null
    }

}