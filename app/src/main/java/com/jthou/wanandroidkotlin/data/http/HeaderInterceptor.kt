package com.jthou.wanandroidkotlin.data.http

import com.jthou.wanandroidkotlin.utils.Preference
import okhttp3.Interceptor
import okhttp3.Response

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 18-08-2019
 */
class HeaderInterceptor : Interceptor {
    /**
     * Created by chenxz on 2018/6/9.
     */
    object HttpConstant {

        const val DEFAULT_TIMEOUT: Long = 15
        const val SAVE_USER_LOGIN_KEY = "user/login"
        const val SAVE_USER_REGISTER_KEY = "user/register"

        const val COLLECTIONS_WEBSITE = "lg/collect"
        const val UNCOLLECTIONS_WEBSITE = "lg/uncollect"
        const val ARTICLE_WEBSITE = "article"
        const val TODO_WEBSITE = "lg/todo"

        const val SET_COOKIE_KEY = "set-cookie"
        const val COOKIE_NAME = "Cookie"

        const val MAX_CACHE_SIZE: Long = 1024 * 1024 * 50 // 50M 的缓存大小

        fun encodeCookie(cookies: List<String>): String {
            val sb = StringBuilder()
            val set = HashSet<String>()
            cookies
                .map { cookie ->
                    cookie.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                }
                .forEach {
                    it.filterNot { set.contains(it) }.forEach { set.add(it) }
                }
            val ite = set.iterator()
            while (ite.hasNext()) {
                val cookie = ite.next()
                sb.append(cookie).append(";")
            }
            val last = sb.lastIndexOf(";")
            if (sb.length - 1 == last) {
                sb.deleteCharAt(last)
            }
            return sb.toString()
        }

        fun saveCookie(url: String?, domain: String?, cookies: String) {
            url ?: return
            var spUrl: String by Preference(url, cookies)
            @Suppress("UNUSED_VALUE")
            spUrl = cookies
            domain ?: return
            var spDomain: String by Preference(domain, cookies)
            @Suppress("UNUSED_VALUE")
            spDomain = cookies
        }

    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        builder.addHeader("Content-type", "application/json; charset=utf-8")

        val domain = request.url.host
        val url = request.url.toString()
        if (domain.isNotEmpty() && (url.contains(HttpConstant.COLLECTIONS_WEBSITE)
                    || url.contains(HttpConstant.UNCOLLECTIONS_WEBSITE)
                    || url.contains(HttpConstant.ARTICLE_WEBSITE)
                    || url.contains(HttpConstant.TODO_WEBSITE))) {
            val spDomain: String by Preference(domain, "")
            val cookie: String = if (spDomain.isNotEmpty()) spDomain else ""
            if (cookie.isNotEmpty()) {
                // 将 Cookie 添加到请求头
                builder.addHeader(HttpConstant.COOKIE_NAME, cookie)
            }
        }

        return chain.proceed(builder.build())
    }

}