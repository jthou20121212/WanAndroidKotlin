package com.jthou.wanandroidkotlin.viewmodel

import androidx.lifecycle.ViewModel
import com.jthou.wanandroidkotlin.repository.KnowledgeSystemListRepository

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 22-09-2019
 */
class KnowledgeSystemListViewModel constructor(private val repository: KnowledgeSystemListRepository) : ViewModel() {

    fun getKnowledgeSystemArticleList(cid: Int) = repository.getKnowledgeSystemArticleList(cid)

}