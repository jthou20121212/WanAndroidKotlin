package com.jthou.wanandroidkotlin.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.jthou.wanandroidkotlin.data.DataRepository
import com.jthou.wanandroidkotlin.data.entity.AbstractResponse
import com.jthou.wanandroidkotlin.data.entity.Article
import com.jthou.wanandroidkotlin.data.entity.KnowledgeSystem

/**
 * @author jthou
 * @version 1.0.0
 * @date 17-08-2019
 */
class KnowledgeSystemRepository internal constructor(private val dataRepository: DataRepository) {

    fun getKnowledgeSystemList(): LiveData<AbstractResponse<List<KnowledgeSystem>>>  = dataRepository.getKnowledgeSystemList()

}
