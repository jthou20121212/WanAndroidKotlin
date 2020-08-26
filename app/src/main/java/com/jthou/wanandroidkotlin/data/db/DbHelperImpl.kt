package com.jthou.wanandroidkotlin.data.db

import android.nfc.tech.MifareUltralight
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.jthou.wanandroidkotlin.base.App
import com.jthou.wanandroidkotlin.data.db.greendao.DaoMaster
import com.jthou.wanandroidkotlin.data.db.greendao.DaoSession
import com.jthou.wanandroidkotlin.data.entity.SearchHistory

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 24-08-2019
 */
class DbHelperImpl constructor() : DbHelper {

    companion object {
        private const val ENCRYPTED = true
        private const val DB_NAME = "com.jthou.wanandroidkotlin.db"
        private const val PAGE_SIZE = 10
    }

    private val mDevOpenHelper: DaoMaster.DevOpenHelper
    private val mDaoMaster: DaoMaster
    private val mDaoSession: DaoSession

    init {
        this.mDevOpenHelper = DaoMaster.DevOpenHelper(App.context, DB_NAME)
        this.mDaoMaster = DaoMaster(this.mDevOpenHelper.writableDatabase)
        this.mDaoSession = this.mDaoMaster.newSession()
    }

    override fun insertDataSearchHistory(data: SearchHistory) {
        this.mDaoSession.searchHistoryDao.insertOrReplace(data)
    }

    override fun clearSearchHistory() {
        this.mDaoSession.searchHistoryDao.deleteAll()
    }

    override fun querySearchHistory(): LiveData<PagedList<SearchHistory>> {
        return LivePagedListBuilder(
            object : DataSource.Factory<Int, SearchHistory>() {
                override fun create(): DataSource<Int, SearchHistory> {
                    return object : PageKeyedDataSource<Int, SearchHistory>() {
                        override fun loadInitial(
                            params: LoadInitialParams<Int>,
                            callback: LoadInitialCallback<Int, SearchHistory>
                        ) {
                            callback.onResult(querySearchHistoryReal(0), 0, 1)
                        }

                        override fun loadAfter(
                            params: LoadParams<Int>,
                            callback: LoadCallback<Int, SearchHistory>
                        ) {
                            callback.onResult(querySearchHistoryReal(params.key), params.key + 1)
                        }

                        override fun loadBefore(
                            params: LoadParams<Int>,
                            callback: LoadCallback<Int, SearchHistory>
                        ) {

                        }

                    }
                }
            }, PagedList.Config.Builder()
                .setPageSize(MifareUltralight.PAGE_SIZE)                         // 配置分页加载的数量
                .setEnablePlaceholders(false)                   // 配置是否启动PlaceHolders
                .setInitialLoadSizeHint(MifareUltralight.PAGE_SIZE)              // 初始化加载的数量
                .build()
        ).build()
    }

    private fun querySearchHistoryReal(page: Int): List<SearchHistory> {
        return this
            .mDaoSession
            .searchHistoryDao
            .queryBuilder()
            .limit(PAGE_SIZE)
            .offset(page)
            .build()
            .list()
    }

}