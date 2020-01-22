package com.achek.hackernews.data.comment.model

import com.achek.hackernews.data.common.model.Item

class CommentTreeParentModel(val item: Item) {
    val key: Int = item.id
    var size = 0
    var kids: MutableList<CommentTreeModel>? = null
    val lvl: Int = 0

    fun addCommentTreeModel(commentTreeModel: CommentTreeModel) {
        if (commentTreeModel.parent == key) {
            commentTreeModel.lvl = this.lvl + 1
            if (kids == null) {
                kids = mutableListOf(commentTreeModel)
            } else kids!!.add(commentTreeModel)
        } else {
            val parent: CommentTreeModel =
                findParent(item = commentTreeModel, source = this.kids!!)!!
            commentTreeModel.lvl = parent.lvl + 1
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
            for (tmp in source) {
                val mParent: CommentTreeModel? = tmp.kids?.let { findParent(item, it) }
                if (mParent != null) return mParent
            }
        }

//        return source.map{ it.kids?.let { findParent(item, it) }}.filter { item.parent == it?.key }.first()

        return null
    }

    fun getList(): MutableList<CommentTreeModel> {

        val list = mutableListOf<CommentTreeModel>()

        this.kids?.let {
            for (kid in it) {
                list.addAll(getListInSource(kid))
            }
        }
        return list
    }

    private fun getListInSource(commentTreeModel: CommentTreeModel): MutableList<CommentTreeModel> {
        val list = mutableListOf<CommentTreeModel>()

        list.add(commentTreeModel)
        commentTreeModel.kids?.let {
            for (kid in it) {
                list.addAll(getListInSource(kid))
            }
        }
        return list
    }
}

class CommentTreeModel(val commentModel: CommentModel) {
    val key = commentModel.id
    val parent = commentModel.parent
    var kids: MutableList<CommentTreeModel>? = null
    var lvl = 0
}
