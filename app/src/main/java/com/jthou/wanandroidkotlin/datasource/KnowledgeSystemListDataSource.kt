package com.jthou.wanandroidkotlin.datasource

import androidx.paging.PageKeyedDataSource
import com.jthou.wanandroidkotlin.base.BaseObserver
import com.jthou.wanandroidkotlin.data.api.WanAndroidApi
import com.jthou.wanandroidkotlin.data.entity.AbstractResponse
import com.jthou.wanandroidkotlin.data.entity.Article
import com.jthou.wanandroidkotlin.data.entity.BaseResponse
import com.jthou.wanandroidkotlin.data.http.RetrofitHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 分页获取知识体系列表
 *
 * @author jthou
 * @version 1.0.0
 * @date 22-09-2019
 */
class KnowledgeSystemListDataSource constructor(private val cid : Int) : PageKeyedDataSource<Int, Article>() {

    private lateinit var mApi : WanAndroidApi

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Article>) {
        mApi = RetrofitHelper.api
        mApi.getKnowledgeSystemArticleList(0, cid)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe(object : BaseObserver<AbstractResponse<BaseResponse<Article>>>() {
                override fun onNext(t: AbstractResponse<BaseResponse<Article>>) {
                    t.data?.datas?.let {
                        it -> callback.onResult(it, 0, 1)
                    }
                }
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        mApi.getKnowledgeSystemArticleList(params.key, cid)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe(object : BaseObserver<AbstractResponse<BaseResponse<Article>>>() {
                override fun onNext(t: AbstractResponse<BaseResponse<Article>>) {
                    t.data?.datas?.let {
                            it -> callback.onResult(it, params.key + 1)
                    }
                }
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
    }


}