package com.jthou.wanandroidkotlin.utils


import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import com.jthou.wanandroidkotlin.base.BaseObserver
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

import java.util.concurrent.TimeUnit

/**
 * Created on 2019/1/14 17:53
 *
 * @author andy
 *
 *
 * 防快速点击辅助类
 */
object ViewClick {

    /**
     * 默认在 500L 毫秒内放置快速点击 2 次及以上。
     */
    private const val DEFAULT_WINDOW_DURATION = 500L

    /**
     * @param view view
     * @param l    View.OnClickListener
     */
    fun clicks(view: View, l: View.OnClickListener?) {
        clicks(view, DEFAULT_WINDOW_DURATION)
            .subscribe(object : BaseObserver<Any>() {
                override fun onNext(t: Any) {
                    l?.onClick(view)
                }
            })
    }

    /**
     * @param view         view
     * @param milliseconds n毫秒内不允许点击2次及以上。
     * @return Observable<Void>
    </Void> */
    @JvmOverloads
    fun clicks(view: View, milliseconds: Long = DEFAULT_WINDOW_DURATION): Observable<Any> {
        return RxView.clicks(view)
            .throttleFirst(milliseconds, TimeUnit.MILLISECONDS)
            .subscribeOn(AndroidSchedulers.mainThread())
    }

    /**
     * @param view         view
     * @param milliseconds n毫秒内不允许点击2次及以上。
     * @param l            View.OnClickListener
     */
    fun clicks(view: View, milliseconds: Long, l: View.OnClickListener?) {
        clicks(view, milliseconds)
            .subscribe(object : BaseObserver<Any>() {
                override fun onNext(t: Any) {
                    l?.onClick(view)
                }
            })
    }
}