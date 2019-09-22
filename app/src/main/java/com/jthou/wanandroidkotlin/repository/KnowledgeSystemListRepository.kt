package com.jthou.wanandroidkotlin.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.jthou.wanandroidkotlin.data.DataRepository
import com.jthou.wanandroidkotlin.data.entity.Article

/**
 * @author jthou
 * @version 1.0.0
 * @date 22-09-2019
 */
class KnowledgeSystemListRepository internal constructor(private val dataRepository: DataRepository) {

    fun getKnowledgeSystemArticleList(cid: Int): LiveData<PagedList<Article>> = dataRepository.getKnowledgeSystemArticleList(cid)

}
