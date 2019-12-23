package com.achek.hackernews.data.common

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppSchedulers : SchedulersProvider {

    override fun io() = Schedulers.io()
    override fun computation() = Schedulers.computation()
    override fun ui() = AndroidSchedulers.mainThread()
}