package com.achek.hackernews.presentation.view.newslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.achek.hackernews.R
import com.achek.hackernews.data.common.model.Item

class NewsAdapter(
    private val clickListener: (Item) -> Unit
): RecyclerView.Adapter<NewsViewHolder>() {

    private  var list = mutableListOf<Item>()
    private  var openModeNews: OpenModeNews? = null

    fun setNewsList(items: List<Item>){
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    fun clearItems(){
        list.clear()
        notifyDataSetChanged()
    }

    fun setMode(openModeNews: OpenModeNews){
        this.openModeNews = openModeNews
    }

    fun addItem(news: Item){
        list.add(news)
        when (openModeNews){
            OpenModeNews.RECENT ->  list.sortBy { - it.time } // munis use instead reverse
//            OpenModeNews.BEST -> list.sortBy { - it.score!! }
//            OpenModeNews.TOP -> list.sortBy { -it.score!! }
//            OpenModeNews.ASK -> list.sortBy { -it.time }
//            OpenModeNews.JOB -> list.sortBy { -it.time }
        }

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