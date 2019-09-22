package com.jthou.wanandroidkotlin.data.entity

class BaseResponse<T> {

    var curPage: Int = 0
    lateinit var datas: List<T>
    var offset: Int = 0
    var isOver: Boolean = false
    var pageCount: Int = 0
    var size: Int = 0
    var total: Int = 0

}
