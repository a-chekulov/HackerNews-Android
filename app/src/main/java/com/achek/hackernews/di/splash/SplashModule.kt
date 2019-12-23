package com.achek.hackernews.di.splash

import com.achek.hackernews.data.common.SchedulersProvider
import com.achek.hackernews.domain.splash.SplashInteractor
import com.achek.hackernews.domain.splash.SplashInteractorImpl
import com.achek.hackernews.presentation.presenter.splash.SplashPresenter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

@Module
class SplashModule {
    @Provides
    fun provideSplashPresenter(
        interactor: SplashInteractor,
        provider: SchedulersProvider,
        cicerone: Cicerone<Router>
    ) : SplashPresenter {
        return SplashPresenter(interactor, provider, cicerone)
    }

    @Provides
    fun provideSplashInteractor(provider: SchedulersProvider): SplashInteractor {
        return SplashInteractorImpl(provider)
    }
}