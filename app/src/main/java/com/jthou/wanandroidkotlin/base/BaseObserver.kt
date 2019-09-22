package com.jthou.wanandroidkotlin.base

import com.blankj.utilcode.util.ToastUtils
import io.reactivex.observers.ResourceObserver

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 04-09-2019
 */
open class BaseObserver<T> : ResourceObserver<T>() {

    override fun onComplete() {

    }

    override fun onNext(t: T) {

    }

    override fun onError(e: Throwable) {
        ToastUtils.showShort("出现异常")
    }

}