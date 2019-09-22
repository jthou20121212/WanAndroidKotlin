package com.jthou.wanandroidkotlin.repository

import com.jthou.wanandroidkotlin.data.DataRepository

/**
 * @author jthou
 * @version 1.0.0
 * @date 08-09-2019
 */
class ProjectRepository internal constructor(private val dataRepository: DataRepository) {

    fun getProjectType() = dataRepository.getProjectType()

    fun getProjectList(cid: Int) = dataRepository.getProjectList(cid)

}
