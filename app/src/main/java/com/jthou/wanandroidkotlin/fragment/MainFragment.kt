package com.jthou.wanandroidkotlin.fragment

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.jthou.wanandroidkotlin.R
import com.jthou.wanandroidkotlin.activity.ArticleDetailActivity
import com.jthou.wanandroidkotlin.adapter.ArticleAdapter
import com.jthou.wanandroidkotlin.base.BaseFragment
import com.jthou.wanandroidkotlin.data.entity.Banner
import com.jthou.wanandroidkotlin.databinding.FragmentMainBinding
import com.jthou.wanandroidkotlin.utils.Constant
import com.jthou.wanandroidkotlin.utils.Injection
import com.jthou.wanandroidkotlin.viewmodel.MainViewModel
import com.jthou.wanandroidkotlin.viewmodel.Provider
import kotlinx.android.synthetic.main.item_banner.*

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 11-08-2019
 */
class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

    override fun createViewModel() = Provider.mainViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mDataBinding = FragmentMainBinding.inflate(inflater, container, false)
        return mDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ArticleAdapter()
        mDataBinding.recyclerView.adapter = adapter
        mDataBinding.recyclerView.layoutManager = LinearLayoutManager(context)

        viewPager.pageMargin = 20
        viewPager.offscreenPageLimit = 3

        mViewModel.getArticleList().observe(this, Observer { adapter.submitList(it) })

        mViewModel.getBannerList().observe(this, Observer {
            it.data?.let {
                setupHeader(it)
            }
        })
    }

    private fun setupHeader(data : List<Banner>) {
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                if (!isAdded) return
                if (data.isEmpty()) return
                val index = position % data.size
                tv_title.text = data[index].title
                tv_index.text = getString(R.string.index_count, index + 1, data.size)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
        viewPager.setOnItemClickListener { position ->
            val index = position % data.size
            val intent = Intent(context, ArticleDetailActivity::class.java)
            intent.putExtra(Constant.ArgumentKey.IT_IS_BANNER, true)
            intent.putExtra(Constant.ArgumentKey.ARTICLE_ID, data[index].id)
            intent.putExtra(Constant.ArgumentKey.ARTICLE_LINK, data[index].url)
            intent.putExtra(Constant.ArgumentKey.ARTICLE_TITLE, data[index].title)
            startActivity(intent)
        }

        val adapter = object : PagerAdapter() {

            override fun getCount() = data.size

            override fun isViewFromObject(@NonNull view: View, @NonNull `object`: Any): Boolean {
                return view === `object`
            }

            @NonNull
            override fun instantiateItem(@NonNull container: ViewGroup, position: Int): Any {
                val imageView = AppCompatImageView(context)
                if (!Injection.getDataRepository().isNoImageMode().value!!) {
                    Glide
                        .with(context!!)
                        .load(data[position].imagePath)
                        .centerCrop()
                        .placeholder(ColorDrawable(ContextCompat.getColor(context!!, R.color.image_holder)))
                        .into(imageView)
                }
                container.addView(imageView)
                return imageView
            }

            override fun destroyItem(@NonNull container: ViewGroup, position: Int, @NonNull `object`: Any) {
                container.removeView(`object` as View)
            }

        }
        viewPager.adapter = adapter
        viewPager.startAutoPlay()
    }

}