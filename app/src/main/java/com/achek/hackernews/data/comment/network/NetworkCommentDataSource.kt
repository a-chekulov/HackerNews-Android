package com.achek.hackernews.data.comment.network

import com.achek.hackernews.data.comment.CommentDataSource
import com.achek.hackernews.data.comment.model.CommentModel
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class NetworkCommentDataSource(retrofit: Retrofit): CommentDataSource {

    private val api = retrofit.create(RetrofitCommentApi::class.java)

    override fun getCommentById(id: Int): Single<CommentModel> {
        return  Single.create{ emitter ->
            api.getCommentById(id).enqueue(object : Callback<CommentModel> {
                override fun onFailure(call: Call<CommentModel>, t: Throwable) {
                    emitter.onError(t)
                }
                override fun onResponse(call: Call<CommentModel>, response: Response<CommentModel>) {
                    if (response.code() == 200) {
                        emitter.onSuccess(response.body()!!)
                    }
                }
            })
        }
    }
}