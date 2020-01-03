package com.achek.hackernews.presentation.view.news

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class NewsScreen(private val id: Int) : SupportAppScreen() {
    override fun getFragment(): Fragment {
        return NewsFragment.NewInstance(id = id)
    }
}
