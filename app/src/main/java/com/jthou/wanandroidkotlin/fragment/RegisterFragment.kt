package com.jthou.wanandroidkotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.SnackbarUtils
import com.jthou.wanandroidkotlin.R
import com.jthou.wanandroidkotlin.base.BaseFragment
import com.jthou.wanandroidkotlin.databinding.FragmentRegisterBinding
import com.jthou.wanandroidkotlin.event.LoginEvent
import com.jthou.wanandroidkotlin.viewmodel.Provider
import com.jthou.wanandroidkotlin.viewmodel.RegisterViewModel
import org.greenrobot.eventbus.EventBus

/**
 *
 *
 * @author jthou
 * @version 5.6.0
 * @date 13-09-2019
 */
class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>() {

    override fun createViewModel(): ViewModelProvider.NewInstanceFactory = Provider.registerViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mDataBinding = FragmentRegisterBinding.inflate(inflater, container, false)
        return mDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun register() {
        val username = mDataBinding.etUsername.text.toString()
        val password = mDataBinding.etPassword.text.toString()
        val repassword = mDataBinding.etRepassword.text.toString()
        if (username.isEmpty() || password.isEmpty() || repassword.isEmpty()) {
            SnackbarUtils.with(mDataBinding.root.rootView).setBottomMargin(BarUtils.getNavBarHeight()).setMessage(getString(
                R.string.account_password_null_tint)).show()
            return
        }
        mViewModel
            .register(username, password, repassword)
            .observe(this, Observer { it ->
                it.data.let {
                    EventBus.getDefault().post(LoginEvent(it))
                }
            })
    }

}