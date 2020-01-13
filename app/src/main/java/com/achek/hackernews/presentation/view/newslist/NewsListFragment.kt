package com.achek.hackernews.presentation.view.newslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.achek.hackernews.R
import com.achek.hackernews.data.common.model.Item
import com.achek.hackernews.data.newslist.NewsRepo
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
    private lateinit var spinner: AppCompatSpinner
    private lateinit var refresh: SwipeRefreshLayout
    private var page = 1

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
        toolbar.title = null
        toolbar.setTitleTextColor(ContextCompat.getColor(activity!!, android.R.color.white))

        initSpinner()

        refresh = view.findViewById(R.id.refresh_layout)

        refresh.setOnRefreshListener {
            refreshState(spinner.selectedItemPosition)
        }
        progress = view.findViewById(R.id.progress)

        adapter = NewsAdapter { if (it.type == "story") presenter.showNews(it.id) }
        recyclerView = view.findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(activity!!)
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val pos =
                        (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                    if (pos == (NewsRepo.PAGE_SIZE) * page - 1) {
                        page++
                        showProgress(true)
                        presenter.loadNews()
                    }
                }
            }
        })
    }

    private fun initSpinner() {
        spinner = toolbar.findViewById(R.id.action_bar_spinner)
        val spAdapter =
            ArrayAdapter.createFromResource(activity!!, R.array.spinner_list, R.layout.spinner_item)
        spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
              refreshState(position)
            }
        }
    }

    private fun refreshState(position: Int){
        adapter.clearItems()
        page = 1
        presenter.refresh(position)
    }

    override fun showMessage(text: String) {
        Toast.makeText(activity!!, text, Toast.LENGTH_SHORT).show()
    }

    override fun showContent(list: List<Item>) {
        adapter.setNewsList(list)
        adapter.notifyDataSetChanged()
    }

    override fun addNews(item: Item){
        adapter.addItem(item)
    }

    override fun showProgress(showing: Boolean) {
        if (showing) progress.visibility = View.VISIBLE
        else progress.visibility = View.GONE
    }

    override fun isRefreshing(show: Boolean) {
        refresh.isRefreshing = show
    }

    override fun setMode(openModeNews: OpenModeNews) {
        adapter.setMode(openModeNews)
    }
}