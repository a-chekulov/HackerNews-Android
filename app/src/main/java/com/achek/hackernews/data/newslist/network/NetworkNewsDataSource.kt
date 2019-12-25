package com.achek.hackernews.data.newslist.network

import com.achek.hackernews.data.newslist.NewsDataSource
import com.achek.hackernews.data.newslist.model.NewsModel
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class NetworkNewsDataSource(retrofit: Retrofit): NewsDataSource{

    private val api = retrofit.create(RetrofitNewsApi::class.java)

    override fun getNewsById(id: Int): Single<NewsModel> {
        return Single.create {emitter ->
            api.getNewsById(id).enqueue(object : Callback<NewsModel>{
                override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                    emitter.onError(t)
                }
                override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                    if (response.code() == 200) {
                        emitter.onSuccess(response.body()!!)
                    }
                }
            })
        }
    }

    override fun getRecentNewsIds(): Single<Array<Int>> {
        return Single.create {emitter ->
            api.getNewsIds().enqueue(object : Callback<Array<Int>>{
                override fun onFailure(call: Call<Array<Int>>, t: Throwable) {
                    emitter.onError(t)
                }
                override fun onResponse(call: Call<Array<Int>>, response: Response<Array<Int>>) {
                    if (response.code() == 200) {
                        emitter.onSuccess(response.body()!!)
                    }
                }
            })
        }
    }

    override fun saveNewsIds(arr: Array<Int>): Completable {
        throw NotImplementedError()
    }

    override fun getNewsIdsByPage(page: Int): Single<Array<Int>> {
        throw NotImplementedError()
    }
}