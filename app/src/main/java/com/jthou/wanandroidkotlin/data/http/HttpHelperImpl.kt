package com.jthou.wanandroidkotlin.data.http

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jthou.wanandroidkotlin.data.entity.*
import com.jthou.wanandroidkotlin.datasource.*
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 24-08-2019
 */
class HttpHelperImpl : HttpHelper {

    override fun getBannerList(): LiveData<AbstractResponse<List<Banner>>> =
        LiveDataReactiveStreams.fromPublisher(
            RetrofitHelper.api.bannerList.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).toFlowable(
                BackpressureStrategy.LATEST
            )
        )

    override fun getKnowledgeSystemArticleList(cid: Int) = LivePagedListBuilder(
        object : DataSource.Factory<Int, Article>() {
            override fun create(): DataSource<Int, Article> {
                return KnowledgeSystemListDataSource(cid)
            }
        }, PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)                         // 配置分页加载的数量
            .setEnablePlaceholders(false)                   // 配置是否启动PlaceHolders
            .setInitialLoadSizeHint(PAGE_SIZE)              // 初始化加载的数量
            .build()
    ).build()

    override fun getSearchList(keyword: String) = LivePagedListBuilder(
        object : DataSource.Factory<Int, Article>() {
            override fun create(): DataSource<Int, Article> {
                return SearchListDataSource(keyword)
            }
        }, PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)                         // 配置分页加载的数量
            .setEnablePlaceholders(false)                   // 配置是否启动PlaceHolders
            .setInitialLoadSizeHint(PAGE_SIZE)              // 初始化加载的数量
            .build()
    ).build()

    override fun getHotSearchList() = LiveDataReactiveStreams.fromPublisher(
        RetrofitHelper.api.hotKeyList.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).toFlowable(
            BackpressureStrategy.LATEST
        )
    )

    override fun getFavoriteList() = LivePagedListBuilder(
        object : DataSource.Factory<Int, Article>() {
            override fun create(): DataSource<Int, Article> {
                return FavoriteListDataSource()
            }
        }, PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)                         // 配置分页加载的数量
            .setEnablePlaceholders(false)                   // 配置是否启动PlaceHolders
            .setInitialLoadSizeHint(PAGE_SIZE)              // 初始化加载的数量
            .build()
    ).build()

    override fun register(username: String, password: String, repassword: String) =
        LiveDataReactiveStreams.fromPublisher(
            RetrofitHelper.api.register(
                username,
                password,
                repassword
            ).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).toFlowable(
                BackpressureStrategy.LATEST
            )
        )

    override fun login(username: String, password: String) = LiveDataReactiveStreams.fromPublisher(
        RetrofitHelper.api.login(
            username,
            password
        ).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).toFlowable(
            BackpressureStrategy.LATEST
        )
    )

    override fun getProjectType() =
        LiveDataReactiveStreams.fromPublisher(
            RetrofitHelper.api.projectType.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).toFlowable(
                BackpressureStrategy.LATEST
            )
        )

    override fun getProjectList(cid: Int) = LivePagedListBuilder(
        object : DataSource.Factory<Int, Article>() {
            override fun create(): DataSource<Int, Article> {
                return ProjectListDataSource(cid)
            }
        }, PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)                         // 配置分页加载的数量
            .setEnablePlaceholders(false)                   // 配置是否启动PlaceHolders
            .setInitialLoadSizeHint(PAGE_SIZE)              // 初始化加载的数量
            .build()
    ).build()

    override fun getNavigationData(): LiveData<AbstractResponse<List<Navigation>>> =
        LiveDataReactiveStreams.fromPublisher(
            RetrofitHelper.api.navigationData.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).toFlowable(
                BackpressureStrategy.LATEST
            )
        )

    override fun getKnowledgeSystemList(): LiveData<AbstractResponse<List<KnowledgeSystem>>> =
        LiveDataReactiveStreams.fromPublisher(
            RetrofitHelper.api.knowledgeSystemList.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).toFlowable(
                BackpressureStrategy.LATEST
            )
        )

    override fun getArticleList(): LiveData<PagedList<Article>> = LivePagedListBuilder(
        object : DataSource.Factory<Int, Article>() {
            override fun create(): DataSource<Int, Article> {
                return ArticleListDataSource()
            }
        }, PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)                         // 配置分页加载的数量
            .setEnablePlaceholders(false)                   // 配置是否启动PlaceHolders
            .setInitialLoadSizeHint(PAGE_SIZE)              // 初始化加载的数量
            .build()
    ).build()

}