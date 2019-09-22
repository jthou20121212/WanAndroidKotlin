package com.jthou.wanandroidkotlin.base

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jthou.wanandroidkotlin.viewmodel.DefaultViewModel
import com.jthou.wanandroidkotlin.viewmodel.DefaultViewModelFactory

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 16-07-2019
 */
abstract class BaseFragment<DB : ViewDataBinding, VM : ViewModel> : Fragment() {

    lateinit var mDataBinding: DB

    val mViewModel: VM by lazy {
        mViewModelDelegate as VM
    }

    private val mViewModelDelegate: ViewModel by viewModels {
        createViewModel()
    }

    open fun createViewModel(): ViewModelProvider.NewInstanceFactory {
        return DefaultViewModelFactory(DefaultViewModel())
    }

}
