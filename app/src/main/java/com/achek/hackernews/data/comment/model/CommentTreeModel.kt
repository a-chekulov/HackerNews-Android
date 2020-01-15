package com.achek.hackernews.data.comment.model

import com.achek.hackernews.data.common.model.Item

class CommentTreeParentModel(val item: Item) {
    val key: Int = item.id
    var size = 0
    var kids: MutableList<CommentTreeModel>? = null

    fun addCommentTreeModel(commentTreeModel: CommentTreeModel) {
        if (commentTreeModel.parent == key) {
            if (kids == null) kids = mutableListOf(commentTreeModel)
            else kids!!.add(commentTreeModel)
        } else {
            val parent: CommentTreeModel = findParent(item = commentTreeModel, source = this.kids!!)!!
            if (parent.kids == null) parent.kids = mutableListOf(commentTreeModel)
            else parent.kids!!.add(commentTreeModel)
        }
        size++
    }

    fun findParent(
        item: CommentTreeModel,
        source: MutableList<CommentTreeModel>
    ): CommentTreeModel? {
        val parent: CommentTreeModel? = source.find { it.key == item.parent }
        if (parent != null) return parent

        else {
            for ( tmp in source){
                val mParent: CommentTreeModel? = tmp.kids?.let { findParent(item, it) }
                if (mParent != null) return mParent
            }
        }

//        return source.map{ it.kids?.let { findParent(item, it) }}.filter { item.parent == it?.key }.first()
        return null
    }
}

class CommentTreeModel(val commentModel: CommentModel) {
    val key = commentModel.id
    val parent = commentModel.parent
    var kids: MutableList<CommentTreeModel>? = null
}
