package com.jthou.wanandroidkotlin.data.http

import okhttp3.Interceptor
import okhttp3.Response

/**
 * 保存cookie
 *
 * @author jthou
 * @version 1.0.0
 * @date 18-08-2019
 */
class SaveCookieInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        val url = request.url.toString()
        val host = request.url.host

        if ((url.contains(HeaderInterceptor.HttpConstant.SAVE_USER_LOGIN_KEY)
                    || url.contains(HeaderInterceptor.HttpConstant.SAVE_USER_REGISTER_KEY))
            && response.headers(HeaderInterceptor.HttpConstant.SET_COOKIE_KEY).isNotEmpty()) {
            val cookies = response.headers(HeaderInterceptor.HttpConstant.SET_COOKIE_KEY)
            val cookie = HeaderInterceptor.HttpConstant.encodeCookie(cookies)
            HeaderInterceptor.HttpConstant.saveCookie(url, host, cookie)
        }
        return response
    }

}