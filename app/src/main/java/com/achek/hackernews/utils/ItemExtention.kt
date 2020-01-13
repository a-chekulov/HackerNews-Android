package com.achek.hackernews.utils

import com.achek.hackernews.data.common.model.Item
import com.achek.hackernews.data.newslist.model.*

fun Item.toStory(): NewsModel = NewsModel(
    this.by,
    this.descendants!!,
    this.id,
    this.kids,
    this.score!!,
    this.time,
    this.title!!,
    this.type,
    this.url
)

fun Item.totAsk(): AskModel = AskModel(
    this.by,
    this.descendants!!,
    this.id,
    this.kids,
    this.score!!,
    this.text!!,
    this.time,
    this.title!!,
    this.type,
    this.url
)

fun Item.toJob(): JobModel = JobModel(
    this.by,
    this.id,
    this.score!!,
    this.text!!,
    this.time,
    this.title!!,
    this.type,
    this.url!!
)

fun Item.toPoll(): PollModel = PollModel(
        this.by,
        this.descendants!!,
        this.id,
        this.kids,
        this.parts,
        this.score!!,
        this.text!!,
        this.time,
        this.title!!,
        this.type
)

fun Item.toPollOpt(): PollOptModel = PollOptModel(
    this.by,
    this.id,
    this.poll!!,
    this.score!!,
    this.text!!,
    this.id,
    this.type
)
