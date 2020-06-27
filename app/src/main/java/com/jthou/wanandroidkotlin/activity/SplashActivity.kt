package com.jthou.wanandroidkotlin.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.jthou.wanandroidkotlin.R
import com.jthou.wanandroidkotlin.base.BaseActivity
import com.jthou.wanandroidkotlin.databinding.ActivitySplashBinding
import com.jthou.wanandroidkotlin.viewmodel.DefaultViewModel

class SplashActivity : BaseActivity<ActivitySplashBinding, DefaultViewModel>() {

    companion object {
        const val DELAY = 1000L
    }

    private val mHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBinding.shimmerViewContainer.startShimmer()
        mHandler.postDelayed({
            startActivity(Intent(SplashActivity@this, MainActivity::class.java))
            overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_fade_out)
            finish()
        }, DELAY)
    }

    override fun resource(): Int {
        return R.layout.activity_splash;
    }


}