package com.jthou.wanandroidkotlin.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.SnackbarUtils
import com.jthou.wanandroidkotlin.R
import com.jthou.wanandroidkotlin.base.BaseFragment
import com.jthou.wanandroidkotlin.databinding.FragmentSettingBinding
import com.jthou.wanandroidkotlin.viewmodel.Provider
import com.jthou.wanandroidkotlin.viewmodel.SettingViewModel


/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 13-09-2019
 */
class SettingFragment : BaseFragment<FragmentSettingBinding, SettingViewModel>() {

    override fun createViewModel() = Provider.settingViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mDataBinding = FragmentSettingBinding.inflate(inflater, container, false)
        mDataBinding.fragment = this
        return mDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mDataBinding.apply {
            mViewModel.isAutoCache().observe(viewLifecycleOwner, Observer { cbSettingCache.isChecked = it })
            mViewModel.isNightMode().observe(viewLifecycleOwner, Observer { cbSettingNight.isChecked = it })
            mViewModel.isNoImageMode().observe(viewLifecycleOwner, Observer { cbSettingImage.isChecked = it })
        }
    }

    fun autoCache() {
        mViewModel.setAutoCache(mDataBinding.cbSettingCache.isChecked)
    }

    fun noImageMode() {
        mViewModel.setNoImageMode(mDataBinding.cbSettingImage.isChecked)
    }

    fun nightMode() {
        mViewModel.setNightMode(mDataBinding.cbSettingNight.isChecked)
        if (mDataBinding.cbSettingNight.isChecked)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        activity?.recreate()
    }

    fun feedback() {
        val data = Intent(Intent.ACTION_SENDTO)
        data.data = Uri.parse(getString(R.string.email_uri_string))
        data.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_title))
        data.resolveActivity(requireContext().packageManager)?.let {
            startActivity(data)
        }
    }

    fun cleanCache() {
        // todo 图片缓存
        // todo webview缓存
    }

    fun checkUpdate() {
        SnackbarUtils.with(mDataBinding.root.rootView).setBottomMargin(BarUtils.getNavBarHeight())
            .setMessage(getString(R.string.check_update_text)).show()
    }

}