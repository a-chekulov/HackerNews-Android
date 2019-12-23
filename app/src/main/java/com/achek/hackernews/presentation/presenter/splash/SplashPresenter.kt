package com.achek.hackernews.presentation.presenter.splash

import com.achek.hackernews.data.common.SchedulersProvider
import com.achek.hackernews.domain.splash.SplashInteractor
import com.achek.hackernews.presentation.view.splash.SplashView
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.squareup.inject.assisted.AssistedInject
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

@InjectViewState
class SplashPresenter @AssistedInject constructor(
    private val interactor: SplashInteractor,
    private val provider: SchedulersProvider,
    private val cicerone: Cicerone<Router>
): MvpPresenter<SplashView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(): SplashPresenter
    }
}