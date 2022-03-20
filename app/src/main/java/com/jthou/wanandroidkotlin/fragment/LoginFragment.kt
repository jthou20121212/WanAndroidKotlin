package com.jthou.wanandroidkotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.SnackbarUtils
import com.jthou.wanandroidkotlin.R
import com.jthou.wanandroidkotlin.base.BaseFragment
import com.jthou.wanandroidkotlin.databinding.FragmentLoginBinding
import com.jthou.wanandroidkotlin.event.LoginEvent
import com.jthou.wanandroidkotlin.event.RegisterEvent
import com.jthou.wanandroidkotlin.viewmodel.LoginViewModel
import com.jthou.wanandroidkotlin.viewmodel.Provider
import org.greenrobot.eventbus.EventBus

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 13-09-2019L
 */
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    override fun createViewModel(): ViewModelProvider.NewInstanceFactory {
        return Provider.loginViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mDataBinding = FragmentLoginBinding.inflate(inflater, container, false)
        mDataBinding.fragment = this
        return mDataBinding.root
    }

    fun login() {
        val username = mDataBinding.idEtUsername.text.toString()
        val password = mDataBinding.idEtPassword.text.toString()
        if (username.isEmpty() || password.isEmpty()) {
            SnackbarUtils.with(mDataBinding.root.rootView)
                .setBottomMargin(BarUtils.getNavBarHeight()).setMessage(
                getString(
                    R.string.account_password_null_tint
                )
            ).show()
            return
        }
        mViewModel
            .login(username, password)
            .observe(this, { it ->
                if (it.data == null) {
                    it.errorMsg?.let {
                        SnackbarUtils.with(mDataBinding.root.rootView)
                            .setBottomMargin(BarUtils.getNavBarHeight()).setMessage(it).show()
                    }
                } else {
                    it.data?.let {
                        EventBus.getDefault().post(LoginEvent(it))
                    }
                }
            })
    }

    fun register() {
        EventBus.getDefault().post(RegisterEvent())
    }

}