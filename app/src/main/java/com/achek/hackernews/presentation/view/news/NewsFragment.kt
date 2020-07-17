package com.achek.hackernews.presentation.view.news

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.achek.hackernews.R
import com.achek.hackernews.data.comment.model.CommentModel
import com.achek.hackernews.data.common.model.Item
import com.achek.hackernews.presentation.presenter.news.NewsPresenter
import com.achek.hackernews.presentation.view.NewsApp
import com.achek.hackernews.utils.dayMonthYearHourMinuteFormat
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import java.util.*

class NewsFragment: MvpAppCompatFragment(), NewsView {

    private var idNews: Int? = null
    private lateinit var toolbar: Toolbar
    private lateinit var titleNews: TextView
    private lateinit var score: TextView
    private lateinit var author: TextView
    private lateinit var time: TextView

    private lateinit var adapter: CommentsAdapter
    private lateinit var recyclerView: RecyclerView


    @InjectPresenter
    lateinit var presenter: NewsPresenter

    @ProvidePresenter
    fun providePresenter(): NewsPresenter {
        val app = activity!!.application as NewsApp
        return app.componentManager
            .buildNewsComponent()
            .newsPresenterFactory
            .create(idNews!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        idNews = arguments!!.getString(ID_KEY)!!.toInt()

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)
        toolbar = view.findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener { presenter.back() }
        titleNews = view.findViewById(R.id.tv_title_news)
        score = view.findViewById(R.id.tv_score)
        author = view.findViewById(R.id.tv_author)
        time = view.findViewById(R.id.tv_time)



        recyclerView = view.findViewById(R.id.recycler_comment)
        recyclerView.layoutManager = LinearLayoutManager(activity!!)


        return view
    }

    override fun showNewsInfo(item: Item) {
        adapter = CommentsAdapter({}, item)
        recyclerView.adapter = adapter

        titleNews.text = item.title
        titleNews.setOnClickListener {
            if (item.url != null && item.url.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
                startActivity(intent);
            }
        }
        score.text = item.score.toString()
        author.text = item.by
        time.text = Date(1000L * item.time).dayMonthYearHourMinuteFormat()
    }

    override fun showMessage(text: String) {
        Toast.makeText(activity!!, text, Toast.LENGTH_SHORT).show()
    }

    override fun addComment(commentModel: CommentModel) {
        commentModel.text?.let { adapter.addItem(commentModel)}
    }

    companion object {
        const val ID_KEY = "id"

        fun NewInstance(id: Int): NewsFragment {
            return NewsFragment().apply {
                arguments = Bundle().apply {
                    putString(ID_KEY, id.toString())
                }
            }
        }
    }
}