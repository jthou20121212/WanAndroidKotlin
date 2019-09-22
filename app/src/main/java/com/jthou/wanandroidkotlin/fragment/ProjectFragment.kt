package com.jthou.wanandroidkotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.jthou.wanandroidkotlin.adapter.ProjectAdapter
import com.jthou.wanandroidkotlin.base.BaseFragment
import com.jthou.wanandroidkotlin.data.entity.Project
import com.jthou.wanandroidkotlin.databinding.FragmentProjectBinding
import com.jthou.wanandroidkotlin.viewmodel.ProjectViewModel
import com.jthou.wanandroidkotlin.viewmodel.Provider

class ProjectFragment : BaseFragment<FragmentProjectBinding, ProjectViewModel>() {
    
    private lateinit var mAdapter: ProjectAdapter

    override fun createViewModel() = Provider.projectViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mDataBinding = FragmentProjectBinding.inflate(inflater, container, false)
        return mDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mDataBinding.recyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter = ProjectAdapter()
        mDataBinding.recyclerView.adapter = mAdapter

        mDataBinding.tabLayout.apply {
            mViewModel.getProjectType().observe(this@ProjectFragment, Observer { it ->
                it.data?.forEach {
                    addTab(newTab().setText(HtmlCompat.fromHtml(it.name, HtmlCompat.FROM_HTML_MODE_LEGACY)).setTag(it))
                }
            })
            addOnTabSelectedListener(object  : TabLayout.OnTabSelectedListener{
                override fun onTabReselected(p0: TabLayout.Tab?) {
                }

                override fun onTabUnselected(p0: TabLayout.Tab?) {
                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.apply {
                        val project = tag as? Project ?: return
                        mViewModel.getProjectList(project.id).observe(this@ProjectFragment, Observer {
                            mAdapter.submitList(it)
                        })
                    }
                }

            })
        }
    }

}
