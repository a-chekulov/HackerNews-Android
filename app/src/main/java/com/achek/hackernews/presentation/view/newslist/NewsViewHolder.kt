package com.achek.hackernews.presentation.view.newslist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.achek.hackernews.R
import com.achek.hackernews.data.common.model.Item
import com.achek.hackernews.utils.dayMonthYearHourMinuteFormat
import java.sql.Date

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title = itemView.findViewById<TextView>(R.id.tv_title_news)
    private val score = itemView.findViewById<TextView>(R.id.tv_score)
    private val time = itemView.findViewById<TextView>(R.id.tv_time)
    private val author = itemView.findViewById<TextView>(R.id.tv_author)
    private val comments = itemView.findViewById<TextView>(R.id.tv_comments)
    private val commentLogo = itemView.findViewById<ImageView>(R.id.iv_comments)

    private val authorPrefix = itemView.context.getString(R.string.author_prefix)


    fun bind(item: Item) {
        author.text = ("$authorPrefix ${item.by}")
        title.text = item.title
        score.text = item.score.toString()
        time.text = Date(1000L * item.time).dayMonthYearHourMinuteFormat()

        if (item.type != "job") {
            comments.visibility = View.VISIBLE
            commentLogo.visibility = View.VISIBLE
            comments.text = item.kids?.let {
                item.kids.size.toString()
            } ?: "0"
        }
        else {
            comments.visibility = View.GONE
            commentLogo.visibility = View.GONE
        }

    }
}