package com.jthou.wanandroidkotlin.fragment

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.SnackbarUtils
import com.bumptech.glide.Glide
import com.jthou.wanandroidkotlin.R
import com.jthou.wanandroidkotlin.base.BaseFragment
import com.jthou.wanandroidkotlin.databinding.FragmentAboutBinding
import com.jthou.wanandroidkotlin.viewmodel.DefaultViewModel


/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 08-09-2019
 */
class AboutFragment : BaseFragment<FragmentAboutBinding, DefaultViewModel>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mDataBinding = FragmentAboutBinding.inflate(inflater, container, false)
        mDataBinding.fragment = this
        return mDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mDataBinding.apply {
            Glide
                .with(ivAbout.context)
                .load(R.drawable.about_header)
                .fitCenter()
                .into(ivAbout)
            tvContent.text = HtmlCompat.fromHtml(getString(com.jthou.wanandroidkotlin.R.string.about_content), HtmlCompat.FROM_HTML_MODE_LEGACY)
            tvProjectAddress.text = HtmlCompat.fromHtml(getString(com.jthou.wanandroidkotlin.R.string.project_address), HtmlCompat.FROM_HTML_MODE_LEGACY)
            tvProjectAddress.movementMethod = LinkMovementMethod.getInstance()
            tvVersion.text = getString(com.jthou.wanandroidkotlin.R.string.version_info, AppUtils.getAppVersionName())
        }
    }

}