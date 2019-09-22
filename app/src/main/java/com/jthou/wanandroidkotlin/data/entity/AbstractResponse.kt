package com.jthou.wanandroidkotlin.data.entity

// 所有的返回结构均为上述，其中errorCode如果为负数则认为错误，此时errorMsg会包含错误信息。data为Object，返回数据根据不同的接口而变化。
class AbstractResponse<T> {

    var data: T? = null
    var errorCode: Int = 0
    var errorMsg: String? = null

    override fun toString(): String {
        return "AbstractResponse{" +
                "data=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\''.toString() +
                '}'.toString()
    }
}
