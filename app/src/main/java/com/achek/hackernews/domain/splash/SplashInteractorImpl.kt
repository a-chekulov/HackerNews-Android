package com.achek.hackernews.domain.splash

import com.achek.hackernews.data.common.SchedulersProvider
import io.reactivex.Completable

class SplashInteractorImpl(private val provider: SchedulersProvider): SplashInteractor {

    // Show Splash 1 second
    override fun showingSplash(): Completable {
        return Completable.fromAction {
            Thread.sleep(1000)
            return@fromAction
        }.subscribeOn(provider.io())
    }
}