package com.achek.hackernews.presentation.view.news

import android.view.View
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.achek.hackernews.R
import com.achek.hackernews.data.comment.model.CommentTreeModel
import com.achek.hackernews.utils.dayMonthYearHourMinuteFormat
import java.sql.Date

class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val author = itemView.findViewById<TextView>(R.id.tv_author)
    private val time = itemView.findViewById<TextView>(R.id.tv_time)
    private val comments = itemView.findViewById<TextView>(R.id.tv_comment)

    // Отступы для вложенных комментариев
    private val comment2 = itemView.findViewById<View>(R.id.comment2)
    private val comment3 = itemView.findViewById<View>(R.id.comment3)

    fun bind(commentTreeModel: CommentTreeModel) {
        author.text = commentTreeModel.commentModel.by
        time.text = Date(1000L * commentTreeModel.commentModel.time).dayMonthYearHourMinuteFormat()
        comments.text = HtmlCompat.fromHtml(commentTreeModel.commentModel.text!!, 0).toString().replace("\n", "")

        when (commentTreeModel.lvl) {
            1 -> {
                comment2.visibility = View.GONE
                comment3.visibility = View.GONE
            }
            2 -> {
                comment2.visibility = View.VISIBLE
                comment3.visibility = View.GONE
            }
            else -> {
                comment2.visibility = View.VISIBLE
                comment3.visibility = View.VISIBLE
            }
        }

    }
}