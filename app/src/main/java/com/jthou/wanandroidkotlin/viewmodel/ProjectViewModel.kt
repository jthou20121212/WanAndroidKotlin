package com.jthou.wanandroidkotlin.viewmodel

import androidx.lifecycle.ViewModel
import com.jthou.wanandroidkotlin.repository.ProjectRepository

class ProjectViewModel constructor(private val repository: ProjectRepository) : ViewModel() {

    fun getProjectType() = repository.getProjectType()

    fun getProjectList(cid: Int) = repository.getProjectList(cid)

}