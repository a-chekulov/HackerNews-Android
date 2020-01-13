package com.achek.hackernews.data.newslist.network

import com.achek.hackernews.data.common.model.Item
import com.achek.hackernews.data.newslist.NewsDataSource
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class NetworkNewsDataSource(retrofit: Retrofit): NewsDataSource{

    private val api = retrofit.create(RetrofitNewsApi::class.java)
    override fun getItemById(id: Int): Single<Item> {
        return Single.create {emitter ->
            api.getItemById(id).enqueue(object : Callback<Item>{
                override fun onFailure(call: Call<Item>, t: Throwable) {
                    emitter.onError(t)
                }
                override fun onResponse(call: Call<Item>, response: Response<Item>) {
                    if (response.code() == 200) {
                        emitter.onSuccess(response.body()!!)
                    }
                }
            })
        }
    }

    override fun getRecentNewsIds(): Single<Array<Int>> {
        return Single.create {emitter ->
            api.getRecentStoriesIds().enqueue(object : Callback<Array<Int>>{
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

    override fun getTopNewsIds(): Single<Array<Int>> {
        return Single.create {emitter ->
            api.getTopStoriesIds().enqueue(object : Callback<Array<Int>>{
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

    override fun getBestNewsIds(): Single<Array<Int>> {
        return Single.create {emitter ->
            api.getBestStoriesIds().enqueue(object : Callback<Array<Int>>{
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

    override fun getAskStoriesIds(): Single<Array<Int>> {
        return Single.create {emitter ->
            api.getAskStoriesIds().enqueue(object : Callback<Array<Int>>{
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

    override fun getShowStoriesIds(): Single<Array<Int>> {
        return Single.create {emitter ->
            api.getShowStoriesIds().enqueue(object : Callback<Array<Int>>{
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

    override fun getJobStoriesIds(): Single<Array<Int>> {
        return Single.create {emitter ->
            api.getJobStoriesIds().enqueue(object : Callback<Array<Int>>{
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

    override fun saveItemsIds(arr: Array<Int>): Completable {
        throw NotImplementedError()
    }

    override fun getItemsIdsByPage(page: Int): Single<Array<Int>> {
        throw NotImplementedError()
    }
}