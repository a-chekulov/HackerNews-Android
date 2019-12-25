package com.achek.hackernews.domain.splash

import io.reactivex.Completable

interface SplashInteractor {
    fun showingSplash(): Completable
}