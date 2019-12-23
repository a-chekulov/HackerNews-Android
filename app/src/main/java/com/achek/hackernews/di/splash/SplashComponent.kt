package com.achek.hackernews.di.splash

import com.achek.hackernews.di.AssistedInjectModule
import com.achek.hackernews.presentation.presenter.splash.SplashPresenter
import dagger.Subcomponent

@Subcomponent(modules =  [SplashModule::class, AssistedInjectModule::class])
interface SplashComponent{
    val splashPresenterFactory: SplashPresenter.Factory
}