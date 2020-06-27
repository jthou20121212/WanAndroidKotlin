package com.jthou.wanandroidkotlin.activity

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.google.android.material.tabs.TabLayout
import com.jthou.wanandroidkotlin.adapter.ArticleAdapter
import com.jthou.wanandroidkotlin.base.BaseActivity
import com.jthou.wanandroidkotlin.data.entity.KnowledgeSystem
import com.jthou.wanandroidkotlin.data.entity.KnowledgeSystemChild
import com.jthou.wanandroidkotlin.databinding.ActivityKnowledgeSystemDetailBinding
import com.jthou.wanandroidkotlin.utils.Constant
import com.jthou.wanandroidkotlin.utils.StatusBarUtils
import com.jthou.wanandroidkotlin.viewmodel.KnowledgeSystemListViewModel
import com.jthou.wanandroidkotlin.viewmodel.Provider
import kotlinx.android.synthetic.main.activity_knowledge_system_detail.*
import java.util.*


/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 22-09-2019
 */
class KnowledgeSystemDetailActivity :

    BaseActivity<ActivityKnowledgeSystemDetailBinding, KnowledgeSystemListViewModel>() {

    private lateinit var mAdapter: ArticleAdapter

    override fun createViewModel() = Provider.knowledgeSystemListViewModel()

    override fun resource() = com.jthou.wanandroidkotlin.R.layout.activity_knowledge_system_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val knowledgeSystem = intent.getParcelableExtra<KnowledgeSystem>(Constant.ArgumentKey.IT_KNOWLEDGE_HIERARCHY)
        mDataBinding.toolbar.title = knowledgeSystem.name
        setSupportActionBar(mDataBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        StatusBarUtils.setStatusColor(window, ContextCompat.getColor(this, com.jthou.wanandroidkotlin.R.color.colorPrimary), 1f)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val tag = tab?.tag as? KnowledgeSystemChild ?: return
                fetchData(tag.id)
            }

        })
        val children = knowledgeSystem.children
        for (k in children) {
            tabLayout.addTab(tabLayout.newTab().setText(k.name?.toLowerCase(Locale.getDefault())).setTag(k))
        }
        tabLayout.getTabAt(0)?.select()

        mAdapter = ArticleAdapter()
        mDataBinding.recyclerView.adapter = mAdapter
        mDataBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        (mDataBinding.recyclerView.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false


        fetchData(knowledgeSystem.children[0].id)
    }

    private fun fetchData(cid: Int) {
        mViewModel.getKnowledgeSystemArticleList(cid).observe(this, Observer {
            mAdapter.submitList(it)
        })

    }


}