package com.achek.hackernews.utils

import com.achek.hackernews.presentation.view.newslist.OpenModeNews

fun Int.getOpenModNews(): OpenModeNews = when (this) {
    0 -> OpenModeNews.RECENT
    1 -> OpenModeNews.TOP
    2 -> OpenModeNews.BEST
    3 -> OpenModeNews.ASK
    4 -> OpenModeNews.JOB
    else -> OpenModeNews.RECENT
}