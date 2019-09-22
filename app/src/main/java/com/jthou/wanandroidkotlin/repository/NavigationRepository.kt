package com.jthou.wanandroidkotlin.repository

import androidx.lifecycle.LiveData
import com.jthou.wanandroidkotlin.data.DataRepository
import com.jthou.wanandroidkotlin.data.entity.AbstractResponse
import com.jthou.wanandroidkotlin.data.entity.Navigation

/**
 * @author jthou
 * @version 1.0.0
 * @date 17-08-2019
 */
class NavigationRepository internal constructor(private val dataRepository: DataRepository) {

    fun getNavigationData(): LiveData<AbstractResponse<List<Navigation>>> = dataRepository.getNavigationData()

}
