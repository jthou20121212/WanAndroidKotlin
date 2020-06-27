package com.jthou.wanandroidkotlin.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jthou.wanandroidkotlin.adapter.NavigationLeftAdapter
import com.jthou.wanandroidkotlin.adapter.NavigationRightAdapter
import com.jthou.wanandroidkotlin.base.BaseFragment
import com.jthou.wanandroidkotlin.data.entity.Navigation
import com.jthou.wanandroidkotlin.databinding.FragmentNavigationBinding
import com.jthou.wanandroidkotlin.event.PositionEvent
import com.jthou.wanandroidkotlin.utils.StickyTitleDecoration
import com.jthou.wanandroidkotlin.viewmodel.NavigationViewModel
import com.jthou.wanandroidkotlin.viewmodel.Provider
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 11-08-2019
 */
class NavigationFragment : BaseFragment<FragmentNavigationBinding, NavigationViewModel>() {

    private lateinit var mLeftData: MutableList<Navigation>
    private lateinit var mRightData: MutableList<Navigation>

    private lateinit var mLeftAdapter: NavigationLeftAdapter
    private lateinit var mRightAdapter: NavigationRightAdapter

    private lateinit var mViewBinding: FragmentNavigationBinding

    private var mTargetPosition: Int = -1
    private lateinit var mOnScrollListener: RecyclerView.OnScrollListener
    private lateinit var mRightLayoutManager: LinearLayoutManager

    override fun createViewModel() = Provider.navigationViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewBinding = FragmentNavigationBinding.inflate(inflater, container, false)
        return mViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewBinding.apply {
            mLeftData = ArrayList()
            mLeftAdapter = NavigationLeftAdapter(mLeftData)
            recycleViewLeft.adapter = mLeftAdapter
            recycleViewLeft.layoutManager = LinearLayoutManager(context)
            recycleViewLeft.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

            mRightData = ArrayList()
            mRightAdapter = NavigationRightAdapter(mRightData)
            mRightLayoutManager = LinearLayoutManager(context)
            mOnScrollListener = object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (this@NavigationFragment.mTargetPosition != -1) {
                        val firstVisibleItemPosition = mRightLayoutManager.findFirstVisibleItemPosition()
                        val position = mTargetPosition - firstVisibleItemPosition
                        mTargetPosition = -1
                        if (position >= 0 && position < recycleViewRight.childCount) {
                            val childAt = recycleViewRight.getChildAt(position)
                            recycleViewRight.scrollBy(0, childAt.top)
                        }
                    }
                }
            }
            recycleViewRight.adapter = mRightAdapter
            recycleViewRight.layoutManager = mRightLayoutManager
            recycleViewRight.addOnScrollListener(mOnScrollListener)
            recycleViewRight.addItemDecoration(StickyTitleDecoration(object : StickyTitleDecoration.GroupNameCallback {
                override fun getGroupName(position: Int): String {
                    return mRightData[position].name
                }

                override fun onGroupNameChange(position: Int) {
                    // 通知左边RecyclerView重新设置选中索引
                    if (this@NavigationFragment.mTargetPosition != -1) {
                        val titleName = mRightData[position].name
                        for (i in mLeftData.indices) {
                            val navigation = mLeftData[i]
                            if (TextUtils.equals(navigation.name, titleName)) {
                                mLeftAdapter.notifyDataSetChanged(i)
                                break
                            }
                        }
                    }
                }

            }))
        }
        mViewModel.getNavigationData().observe(viewLifecycleOwner, Observer { it ->
            it.data?.let {
                mLeftData.addAll(it)
                mLeftAdapter.notifyDataSetChanged()
                mRightData.addAll(it)
                mRightAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        if (EventBus.getDefault().isRegistered(this)) {
            return
        }
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    override fun onDestroyView() {
        mViewBinding.recycleViewRight.removeOnScrollListener(mOnScrollListener)
        super.onDestroyView()
    }

    @Subscribe
    fun onEvent(event : PositionEvent) {
        val position = event.position
        val name = mLeftData[position].name
        var rightPosition = 0
        for (i in mRightData.indices) {
            val navigation = mRightData[i]
            if (TextUtils.equals(name, navigation.name)) {
                rightPosition = i
                break
            }
        }

        mTargetPosition = -1
        val firstVisibleItemPosition = mRightLayoutManager.findFirstVisibleItemPosition()
        val lastVisibleItemPosition = mRightLayoutManager.findLastVisibleItemPosition()
        when {
            rightPosition <= firstVisibleItemPosition -> mViewBinding.recycleViewRight.scrollToPosition(rightPosition)
            rightPosition <= lastVisibleItemPosition -> {
                val childAt = mViewBinding.recycleViewRight.getChildAt(rightPosition - firstVisibleItemPosition)
                mViewBinding.recycleViewRight.scrollBy(0, childAt.top)
            }
            else -> {
                // 这里需要先滚动position所在的位置
                mViewBinding.recycleViewRight.scrollToPosition(rightPosition)
                mTargetPosition = rightPosition
            }
        }
    }

}
