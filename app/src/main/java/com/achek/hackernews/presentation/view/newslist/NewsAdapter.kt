package com.achek.hackernews.presentation.view.newslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.achek.hackernews.R
import com.achek.hackernews.data.newslist.model.NewsModel

class NewsAdapter(
    private val clickListener: (NewsModel) -> Unit
): RecyclerView.Adapter<NewsViewHolder>() {

    private  var list = mutableListOf<NewsModel>()

    fun setNewsList(items: List<NewsModel>){
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    fun clearItems(){
        list.clear()
        notifyDataSetChanged()
    }

    fun addItem(news: NewsModel){
        list.add(news)
        list.sortBy { - it.time } // munis use instead reverse
        notifyItemInserted(list.indexOf(news))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.holder_news, parent, false)
        return NewsViewHolder(view)

    }
    override fun getItemCount(): Int {
        return  list.size

    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener { view ->
            clickListener.invoke(list[position])
        }
    }
}