package com.jthou.wanandroidkotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jthou.wanandroidkotlin.adapter.KnowledgeSystemAdapter
import com.jthou.wanandroidkotlin.base.BaseFragment
import com.jthou.wanandroidkotlin.data.entity.KnowledgeSystem
import com.jthou.wanandroidkotlin.databinding.FragmentKnowledgeSystemBinding
import com.jthou.wanandroidkotlin.viewmodel.KnowledgeSystemViewModel
import com.jthou.wanandroidkotlin.viewmodel.Provider
import java.util.*

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 11-08-2019
 */
class KnowledgeSystemFragment : BaseFragment<FragmentKnowledgeSystemBinding, KnowledgeSystemViewModel>() {

    override fun createViewModel() = Provider.knowledgeSystemViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mDataBinding = FragmentKnowledgeSystemBinding.inflate(inflater, container, false)
        return mDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mDataBinding.recyclerView.layoutManager = LinearLayoutManager(context)

        val data: MutableList<KnowledgeSystem> = ArrayList()
        val adapter = KnowledgeSystemAdapter(requireContext(), data)
        mDataBinding.recyclerView.adapter = adapter
        mViewModel.getKnowledgeSystemList().observe(viewLifecycleOwner, Observer { it ->
            it.data.let {
                data.addAll(it!!)
                adapter.notifyDataSetChanged()
            }
        })
    }

}