package com.achek.hackernews.presentation.view.newslist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.achek.hackernews.R
import com.achek.hackernews.data.newslist.NewsRepo
import com.achek.hackernews.data.newslist.model.NewsModel
import com.achek.hackernews.presentation.presenter.newslist.NewsListPresenter
import com.achek.hackernews.presentation.view.NewsApp
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import javax.inject.Inject


class NewsListFragment : MvpAppCompatFragment(), NewsListView {

    private lateinit var toolbar: Toolbar
    private lateinit var adapter: NewsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progress: ProgressBar
    private  var page = 1

    @Inject
    @InjectPresenter
    lateinit var presenter: NewsListPresenter

    @ProvidePresenter
    fun providePresenter(): NewsListPresenter {
        val app = activity!!.application as NewsApp
        return app.componentManager
            .buildNewsComponent()
            .newsListPresenterFactory
            .create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_newslist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = view.findViewById(R.id.toolbar)
        toolbar.title = getString(R.string.recent_news)
        toolbar.setTitleTextColor(ContextCompat.getColor(activity!!, android.R.color.white))

        progress = view.findViewById(R.id.progress)

        adapter = NewsAdapter {
            try {
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(it.url)
                startActivity(i)
            } catch (ex: NullPointerException) {
                showMessage(ex.toString())
            }

        }
        recyclerView = view.findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(activity!!)
        recyclerView.adapter = adapter


        recyclerView.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val pos = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                    if (pos == (NewsRepo.PAGE_SIZE) * page - 1) {
                        page++
                        showProgress(true)
                        presenter.loadNews()
                    }
                }
            }
        })
    }

    override fun showMessage(text: String) {
        Toast.makeText(activity!!, text, Toast.LENGTH_SHORT).show()
    }

    override fun showContent(list: List<NewsModel>) {
        adapter.setNewsList(list)
        adapter.notifyDataSetChanged()
    }

    override fun addNews(newsModel: NewsModel) {
        adapter.addItem(newsModel)
    }

    override fun showProgress(showing: Boolean) {
        if (showing) progress.visibility = View.VISIBLE
        else progress.visibility = View.GONE
    }
}