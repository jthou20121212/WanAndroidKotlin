package com.jthou.wanandroidkotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jthou.wanandroidkotlin.adapter.ArticleAdapter
import com.jthou.wanandroidkotlin.base.BaseFragment
import com.jthou.wanandroidkotlin.databinding.FragmentFavoriteBinding
import com.jthou.wanandroidkotlin.viewmodel.FavoriteViewModel
import com.jthou.wanandroidkotlin.viewmodel.Provider

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 13-09-2019
 */
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>() {

    override fun createViewModel(): ViewModelProvider.NewInstanceFactory {
        return Provider.favoriteViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mDataBinding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return mDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArticleAdapter()
        mDataBinding.recyclerView.adapter = adapter
        mDataBinding.recyclerView.layoutManager = LinearLayoutManager(context)

        mViewModel.getFavoriteList().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            adapter.submitList(it)
        })
    }

}