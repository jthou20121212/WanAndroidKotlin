package com.jthou.wanandroidkotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jthou.wanandroidkotlin.data.entity.AbstractResponse
import com.jthou.wanandroidkotlin.data.entity.KnowledgeSystem
import com.jthou.wanandroidkotlin.repository.KnowledgeSystemRepository

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 17-08-2019
 */
class KnowledgeSystemViewModel constructor(private val repository: KnowledgeSystemRepository) : ViewModel() {

    fun getKnowledgeSystemList(): LiveData<AbstractResponse<List<KnowledgeSystem>>>  = repository.getKnowledgeSystemList()

}