package com.jthou.wanandroidkotlin.activity

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jthou.wanandroidkotlin.R
import com.jthou.wanandroidkotlin.adapter.ArticleAdapter
import com.jthou.wanandroidkotlin.base.BaseActivity
import com.jthou.wanandroidkotlin.databinding.ActivitySearchListBinding
import com.jthou.wanandroidkotlin.utils.Constant
import com.jthou.wanandroidkotlin.utils.StatusBarUtils
import com.jthou.wanandroidkotlin.viewmodel.Provider
import com.jthou.wanandroidkotlin.viewmodel.SearchListViewModel

/**
 * 搜索列表页
 *
 * @author jthou
 * @version 1.0.0
 * @date 22-09-2019
 */
class SearchListActivity : BaseActivity<ActivitySearchListBinding, SearchListViewModel>() {

    private lateinit var mAdapter: ArticleAdapter

    override fun resource() = R.layout.activity_search_list

    override fun createViewModel() = Provider.searchListViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBinding.tvTitle.text = HtmlCompat.fromHtml(intent.getStringExtra(Constant.ArgumentKey.IT_KEYWORD), HtmlCompat.FROM_HTML_MODE_LEGACY)
        mDataBinding.toolbar.title = getString(R.string.blank_one)
        setSupportActionBar(mDataBinding.toolbar)
        mDataBinding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        StatusBarUtils.setStatusColor(window, ContextCompat.getColor(this@SearchListActivity, R.color.colorPrimary), 1f)

        mDataBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = ArticleAdapter()
        mDataBinding.recyclerView.adapter = mAdapter

        intent?.getStringExtra(Constant.ArgumentKey.IT_KEYWORD)?.let { it ->
            mViewModel.getSearchList(it).observe(this, Observer {
                mAdapter.submitList(it)
            })
        }

    }

}