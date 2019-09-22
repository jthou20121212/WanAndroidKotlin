package com.jthou.wanandroidkotlin.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebSettings
import android.widget.LinearLayout
import androidx.core.text.HtmlCompat
import com.blankj.utilcode.util.NetworkUtils
import com.jthou.wanandroidkotlin.R
import com.jthou.wanandroidkotlin.base.BaseActivity
import com.jthou.wanandroidkotlin.databinding.ActivityArticleDetailBinding
import com.jthou.wanandroidkotlin.utils.Constant
import com.jthou.wanandroidkotlin.utils.Injection
import com.jthou.wanandroidkotlin.utils.StatusBarUtils
import com.jthou.wanandroidkotlin.viewmodel.DefaultViewModel
import com.just.agentweb.AgentWeb

class ArticleDetailActivity : BaseActivity<ActivityArticleDetailBinding, DefaultViewModel>() {
    
    private lateinit var mAgentWeb:  AgentWeb

    private val mFavorite: Boolean by lazy {
        intent.getBooleanExtra(Constant.ArgumentKey.ARTICLE_IS_FAVORITE, false)
    }

    private val mArticleId: Int by lazy {
        intent.getIntExtra(Constant.ArgumentKey.ARTICLE_ID, 0)
    }

    private val mLink: String by lazy {
        intent.getStringExtra(Constant.ArgumentKey.ARTICLE_LINK)
    }

    private val mTitle: String by lazy {
        intent.getStringExtra(Constant.ArgumentKey.ARTICLE_TITLE)
    }

    override fun resource() = R.layout.activity_article_detail

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBinding.toolbar.apply {
            title = HtmlCompat.fromHtml(mTitle, HtmlCompat.FROM_HTML_MODE_LEGACY)
            setSupportActionBar(this)
            setNavigationOnClickListener {
                onBackPressed()
            }
        }
        StatusBarUtils.setPaddingSmart(this, mDataBinding.toolbar)

        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(mDataBinding.webViewContainer, LinearLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
//            .setMainFrameErrorView(com.jthou.wanandroidkotlin.R.layout.error_view, -1)
            .createAgentWeb().ready().go(mLink)

        mAgentWeb.webCreator.webView.settings.apply {
            javaScriptEnabled = true
            setSupportZoom(true)
            builtInZoomControls = true
            // 不显示缩放按钮
            displayZoomControls = false
            // 设置自适应屏幕，两者合用
            // 将图片调整到适合WebView的大小
            useWideViewPort = true
            // 缩放至屏幕的大小
            loadWithOverviewMode = true
            // 自适应屏幕
            layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN

            val noImageMode = Injection.getDataRepository().isNoImageMode().value!!
            blockNetworkImage = noImageMode
            val autoCache = Injection.getDataRepository().isAutoCache().value!!
            setAppCacheEnabled(autoCache)
            domStorageEnabled = autoCache
            databaseEnabled = autoCache
            cacheMode = if (autoCache) {
                if (NetworkUtils.isConnected()) {
                    WebSettings.LOAD_DEFAULT
                } else {
                    WebSettings.LOAD_CACHE_ELSE_NETWORK
                }
            } else {
                WebSettings.LOAD_NO_CACHE
            }
        }
    }
}
