package com.jthou.wanandroidkotlin.utils

import com.jthou.wanandroidkotlin.data.DataRepository
import com.jthou.wanandroidkotlin.data.db.DbHelperImpl
import com.jthou.wanandroidkotlin.data.http.HttpHelperImpl
import com.jthou.wanandroidkotlin.data.sp.SpHelperImpl

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 24-08-2019
 */
object Injection {

    fun getDataRepository() : DataRepository {
        val dbHelper = DbHelperImpl()
        val httpHelper = HttpHelperImpl()
        val preferenceHelper = SpHelperImpl()
        return DataRepository(dbHelper, httpHelper, preferenceHelper)
    }

}