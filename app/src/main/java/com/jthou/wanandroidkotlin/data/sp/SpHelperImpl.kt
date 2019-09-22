package com.jthou.wanandroidkotlin.data.sp

import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.SPUtils
import com.jthou.wanandroidkotlin.utils.Constant

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 24-08-2019
 */
class SpHelperImpl : SpHelper {

    override fun setAutoCache(value: Boolean) = SPUtils.getInstance(Constant.SpKey.SP_NAME).put(Constant.SpKey.SP_AUTO_CACHE, value)

    override fun setNoImageMode(value: Boolean) = SPUtils.getInstance(Constant.SpKey.SP_NAME).put(Constant.SpKey.SP_NO_IMAGE, value)

    override fun setNightMode(value: Boolean) = SPUtils.getInstance(Constant.SpKey.SP_NAME).put(Constant.SpKey.SP_NIGHT_MODE, value)

    override fun isAutoCache() = MutableLiveData(SPUtils.getInstance(Constant.SpKey.SP_NAME).getBoolean(Constant.SpKey.SP_AUTO_CACHE))

    override fun isNoImageMode() = MutableLiveData(SPUtils.getInstance(Constant.SpKey.SP_NAME).getBoolean(Constant.SpKey.SP_NO_IMAGE))

    override fun isNightMode() = MutableLiveData(SPUtils.getInstance(Constant.SpKey.SP_NAME).getBoolean(Constant.SpKey.SP_NIGHT_MODE))

}