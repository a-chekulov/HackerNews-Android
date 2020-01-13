package com.achek.hackernews.presentation.view.news

import android.view.View
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.achek.hackernews.R
import com.achek.hackernews.data.comment.model.CommentModel
import com.achek.hackernews.utils.dayMonthYearHourMinuteFormat
import java.sql.Date

class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val author = itemView.findViewById<TextView>(R.id.tv_author)
    private val time = itemView.findViewById<TextView>(R.id.tv_time)
    private val comments = itemView.findViewById<TextView>(R.id.tv_comment)

    fun bind(commentModel: CommentModel) {
        author.text = commentModel.by
        time.text = Date(1000L * commentModel.time).dayMonthYearHourMinuteFormat()
        comments.text = HtmlCompat.fromHtml(commentModel.text, 0).toString()
    }
}