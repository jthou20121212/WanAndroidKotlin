package com.jthou.wanandroidkotlin.base

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.ScreenUtils
import com.jthou.wanandroidkotlin.R
import com.jthou.wanandroidkotlin.activity.SplashActivity
import com.jthou.wanandroidkotlin.viewmodel.DefaultViewModel
import com.jthou.wanandroidkotlin.viewmodel.DefaultViewModelFactory
import me.dkzwm.widget.esl.EasySwipeLayout
import me.dkzwm.widget.esl.EasySwipeManager
import me.dkzwm.widget.esl.graphics.JIKEDrawer


/**
 *
 * 项目中所有Activity的基类
 * @author jthou
 * @version 1.0.0
 * @date 12-07-2019
 */
abstract class BaseActivity<DB : ViewDataBinding, VM : ViewModel> : AppCompatActivity() {

    val mDataBinding: DB by lazy {
        DataBindingUtil.setContentView<DB>(this, resource())
    }

    val mViewModel: VM by lazy {
        mViewModelDelegate as VM
    }

    private val mViewModelDelegate: ViewModel by viewModels {
        createViewModel()
    }

    open fun createViewModel(): ViewModelProvider.NewInstanceFactory {
        return DefaultViewModelFactory(DefaultViewModel())
    }

    abstract fun resource() : Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (this is SplashActivity) {
            return
        }
        val layout: EasySwipeLayout = EasySwipeManager.attach(this)
        layout.setDurationOfClose(270)
        layout.setEdgeDiff((ScreenUtils.getScreenWidth() * 0.04f).toInt())
        layout.setDrawer(JIKEDrawer(this))
        layout.setDirection(me.dkzwm.widget.esl.config.Constants.DIRECTION_LEFT)
        layout.setSwipeListener { onBackPressed() }
    }

    override fun finish() {
        super.finish()
        if (this is SplashActivity) {
            return
        }
        super.overridePendingTransition(R.anim.activity_exit_slide_in, R.anim.activity_exit_slide_out)
    }

}