package com.achek.hackernews.presentation.view

import android.app.Application
import com.achek.hackernews.di.ComponentManager

class NewsApp: Application(){
    lateinit var componentManager: ComponentManager

    override fun onCreate() {
        super.onCreate()
        componentManager = ComponentManager(this)
    }
}