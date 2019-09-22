package com.jthou.wanandroidkotlin.data.http

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.jthou.wanandroidkotlin.data.entity.*

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 18-08-2019
 */
interface HttpHelper {

    fun getArticleList() : LiveData<PagedList<Article>>

    fun getKnowledgeSystemList() : LiveData<AbstractResponse<List<KnowledgeSystem>>>

    fun getNavigationData() : LiveData<AbstractResponse<List<Navigation>>>

    fun getProjectType() : LiveData<AbstractResponse<List<Project>>>

    fun getProjectList(cid : Int) : LiveData<PagedList<Article>>

    fun login(username: String, password: String) : LiveData<AbstractResponse<LoginData>>

    fun register(username: String, password: String, repassword: String) : LiveData<RegisterData>

    fun getFavoriteList() : LiveData<PagedList<Article>>

    fun getHotSearchList() : LiveData<AbstractResponse<List<HotSearch>>>

    fun getSearchList(keyword : String) : LiveData<PagedList<Article>>

    fun getKnowledgeSystemArticleList(cid : Int) : LiveData<PagedList<Article>>

    fun getBannerList() : LiveData<AbstractResponse<List<Banner>>>

}