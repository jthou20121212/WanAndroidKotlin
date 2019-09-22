package com.jthou.wanandroidkotlin.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.jthou.wanandroidkotlin.data.db.DbHelper
import com.jthou.wanandroidkotlin.data.entity.*
import com.jthou.wanandroidkotlin.data.http.HttpHelper
import com.jthou.wanandroidkotlin.data.sp.SpHelper

/**
 * 数据仓库，所有的数据都经过这里过去
 *
 * @author jthou
 * @version 1.0.0
 * @date 18-08-2019
 */
class DataRepository(val dbHelper: DbHelper, private val httpHelper: HttpHelper, private val spHelper: SpHelper) : DbHelper, HttpHelper, SpHelper {

    override fun getBannerList() = httpHelper.getBannerList()

    override fun getKnowledgeSystemArticleList(cid : Int) = httpHelper.getKnowledgeSystemArticleList(cid)

    override fun getSearchList(keyword : String) = httpHelper.getSearchList(keyword)

    override fun getHotSearchList() = httpHelper.getHotSearchList()

    override fun getFavoriteList() = httpHelper.getFavoriteList()

    override fun register(username: String, password: String, repassword: String) = httpHelper.register(username, password, repassword)

    override fun login(username: String, password: String) = httpHelper.login(username, password)

    override fun setAutoCache(value: Boolean) = spHelper.setAutoCache(value)

    override fun setNoImageMode(value: Boolean) = spHelper.setNoImageMode(value)

    override fun setNightMode(value: Boolean) = spHelper.setNightMode(value)

    override fun isAutoCache() = spHelper.isAutoCache()

    override fun isNoImageMode() = spHelper.isNoImageMode()

    override fun isNightMode() = spHelper.isNightMode()

    override fun getNavigationData(): LiveData<AbstractResponse<List<Navigation>>> = httpHelper.getNavigationData()

    override fun getKnowledgeSystemList(): LiveData<AbstractResponse<List<KnowledgeSystem>>> = httpHelper.getKnowledgeSystemList()

    override fun getArticleList(): LiveData<PagedList<Article>> = httpHelper.getArticleList()

    override fun getProjectType() = httpHelper.getProjectType()

    override fun getProjectList(cid: Int) = httpHelper.getProjectList(cid)

}