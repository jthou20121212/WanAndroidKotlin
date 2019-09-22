package com.jthou.wanandroidkotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.flexbox.FlexboxLayoutManager
import com.jthou.wanandroidkotlin.adapter.HotSearchAdapter
import com.jthou.wanandroidkotlin.base.BaseFragment
import com.jthou.wanandroidkotlin.data.entity.HotSearch
import com.jthou.wanandroidkotlin.databinding.FragmentSearchBinding
import com.jthou.wanandroidkotlin.event.SearchEvent
import com.jthou.wanandroidkotlin.viewmodel.Provider
import com.jthou.wanandroidkotlin.viewmodel.SearchViewModel
import org.greenrobot.eventbus.EventBus

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 14-09-2019
 */
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {

    override fun createViewModel(): ViewModelProvider.NewInstanceFactory {
        return Provider.searchViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mDataBinding = FragmentSearchBinding.inflate(inflater, container, false)
        return mDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mDataBinding.recyclerView.layoutManager = FlexboxLayoutManager(context)
        val data: MutableList<HotSearch> = ArrayList()
        mDataBinding.recyclerView.adapter = HotSearchAdapter(data)
        mViewModel.getHotSearchList().observe(this, Observer { it ->
            it.data?.let {
                data.addAll(it)
                mDataBinding.recyclerView.adapter?.notifyDataSetChanged()
            }
        })
        mDataBinding.searchView.apply {
            isIconified = false
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        EventBus.getDefault().post(SearchEvent(it))
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

            })
        }
    }

}