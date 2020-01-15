package com.achek.hackernews.presentation.view.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.achek.hackernews.R
import com.achek.hackernews.data.comment.model.CommentModel
import com.achek.hackernews.data.comment.model.CommentTreeModel
import com.achek.hackernews.data.comment.model.CommentTreeParentModel
import com.achek.hackernews.data.common.model.Item

class CommentsAdapter(
    private val clickListener: (CommentModel) -> Unit,
    private val parent: Item
): RecyclerView.Adapter<CommentViewHolder>() {

    private  var list = mutableListOf<CommentModel>()
    private  var tree =  CommentTreeParentModel(parent)


    fun addItem(comment: CommentModel){
        list.add(comment)
        tree.addCommentTreeModel(CommentTreeModel(comment))
        list.sortBy { - it.time } // munis use instead reverse
        notifyItemInserted(list.indexOf(comment))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.holder_comment, parent, false)
        return CommentViewHolder(view)

    }
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener { view ->
            clickListener.invoke(list[position])
        }
    }

}