package com.achek.hackernews.presentation.view.splash

import ru.terrakok.cicerone.android.support.SupportAppScreen

class SplashScreen : SupportAppScreen(){
    override fun getFragment(): SplashFragment = SplashFragment()
}