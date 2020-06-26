package com.jthou.wanandroidkotlin.base

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jthou.wanandroidkotlin.R
import com.jthou.wanandroidkotlin.viewmodel.DefaultViewModel
import com.jthou.wanandroidkotlin.viewmodel.DefaultViewModelFactory


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

    override fun finish() {
        super.finish()
        super.overridePendingTransition(R.anim.activity_exit_slide_in, R.anim.activity_exit_slide_out)
    }

}