package com.jthou.wanandroidkotlin.data.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

/**
 * 搜索历史 - greenDAO 不支持使用 kotlin 编写的实体
 *
 * @author jthou
 * @date 04-08-2020
 * @since 1.0.0
 */
@Entity
public class SearchHistory {

    @NotNull
    @Unique
    public String keyword;

    @Generated(hash = 1789055783)
    public SearchHistory(@NotNull String keyword) {
        this.keyword = keyword;
    }

    @Generated(hash = 1905904755)
    public SearchHistory() {
    }

    public String getKeyword() {
        return this.keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
