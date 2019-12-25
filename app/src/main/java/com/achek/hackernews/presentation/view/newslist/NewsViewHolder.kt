package com.achek.hackernews.presentation.view.newslist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.achek.hackernews.R
import com.achek.hackernews.data.newslist.model.NewsModel
import com.achek.hackernews.utils.dayMonthYearHourMinuteFormat
import java.sql.Date

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val logo = itemView.findViewById<ImageView>(R.id.iv_scope)
    private val title = itemView.findViewById<TextView>(R.id.tv_title_news)
    private val score = itemView.findViewById<TextView>(R.id.tv_score)
    private val time = itemView.findViewById<TextView>(R.id.tv_time)

    fun bind(newsModel: NewsModel) {
        title.text = newsModel.title
        score.text = newsModel.score.toString()

        time.text = Date(1000L * newsModel.time).dayMonthYearHourMinuteFormat()
    }
}