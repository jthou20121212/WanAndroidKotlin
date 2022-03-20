package com.jthou.wanandroidkotlin.data.http

import com.jthou.wanandroidkotlin.BuildConfig
import com.jthou.wanandroidkotlin.base.App
import com.jthou.wanandroidkotlin.data.api.WanAndroidApi
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 18-08-2019
 */
object RetrofitHelper {

    private var retrofit: Retrofit? = null

    val api: WanAndroidApi by lazy { getRetrofit().create(WanAndroidApi::class.java) }

    private fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            synchronized(RetrofitHelper::class.java) {
                if (retrofit == null) {
                    retrofit = Retrofit
                        .Builder()
                        .client(getOkhttpClient())
                        .baseUrl(WanAndroidApi.HOST)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
            }
        }
       return retrofit!!
    }

    private fun getOkhttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        //设置 请求的缓存的大小跟位置
        val cacheFile = File(App.context.cacheDir, "cache")
        val cache = Cache(cacheFile, HeaderInterceptor.HttpConstant.MAX_CACHE_SIZE)

        builder.run {
            addInterceptor(httpLoggingInterceptor)
            addInterceptor(HeaderInterceptor())
            addInterceptor(SaveCookieInterceptor())
            addInterceptor(CacheInterceptor())
            cache(cache)  //添加缓存
            connectTimeout(HeaderInterceptor.HttpConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(HeaderInterceptor.HttpConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(HeaderInterceptor.HttpConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true) // 错误重连
            // cookieJar(CookieManager())
        }
        return builder.build()
    }

}