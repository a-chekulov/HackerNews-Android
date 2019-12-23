package com.achek.hackernews.presentation.view.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.achek.hackernews.R
import com.achek.hackernews.presentation.presenter.splash.SplashPresenter
import com.achek.hackernews.presentation.view.NewsApp
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import javax.inject.Inject

class SplashFragment: MvpAppCompatFragment(), SplashView {

    @Inject
    @InjectPresenter
    lateinit var presenter: SplashPresenter

    @ProvidePresenter
    fun providePresenter() : SplashPresenter {
        val app = activity!!.application as NewsApp
        return app.componentManager
            .buildSplashComponent()
            .splashPresenterFactory
            .create()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}