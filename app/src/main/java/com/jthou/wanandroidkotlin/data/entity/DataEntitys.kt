package com.jthou.wanandroidkotlin.data.entity

import android.os.Parcel
import android.os.Parcelable
import com.jthou.wanandroidkotlin.data.IMultipleType

// 轮播图
data class Banner(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
) : IMultipleType {
    override fun getDataType(): Int {
        return TYPE_BANNER
    }
}

// 文章
data class Article (

    val apkLink: String,
    val author: String,
    val chapterId: Int,
    val chapterName: String,
    var collect: Boolean,
    val courseId: Int,
    val desc: String,
    val envelopePic: String,
    val fresh: Boolean,
    val id: Int,
    val link: String,
    val niceDate: String,
    val origin: String,
    val projectLink: String,
    val publishTime: Long,
    val superChapterName: String,
    val tags: MutableList<Tag>,
    val title: String,
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int,
    var top: String
) : IMultipleType {
    override fun getDataType(): Int {
        return TYPE_ARTICLE
    }
}

data class Tag(
    val name: String,
    val url: String
)

// 知识体系
data class KnowledgeSystem(
    val children: MutableList<KnowledgeSystemChild>,
    val courseId: Int,
    val id: Int,
    val name: String?,
    val order: Int,
    val parentChapterId: Int,
    val visible: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readArrayList(KnowledgeSystemChild::class.java.classLoader) as MutableList<KnowledgeSystemChild>,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeList(children as List<*>?)
        parcel.writeInt(courseId)
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(order)
        parcel.writeInt(parentChapterId)
        parcel.writeInt(visible)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<KnowledgeSystem> {
        override fun createFromParcel(parcel: Parcel): KnowledgeSystem {
            return KnowledgeSystem(parcel)
        }

        override fun newArray(size: Int): Array<KnowledgeSystem?> {
            return arrayOfNulls(size)
        }
    }
}

data class KnowledgeSystemChild(
    val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String?,
    val order: Int,
    val parentChapterId: Int,
    val visible: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readArrayList(Any::class.java.classLoader) as MutableList<Any>,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeList(children)
        parcel.writeInt(courseId)
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(order)
        parcel.writeInt(parentChapterId)
        parcel.writeInt(visible)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<KnowledgeSystemChild> {
        override fun createFromParcel(parcel: Parcel): KnowledgeSystemChild {
            return KnowledgeSystemChild(parcel)
        }

        override fun newArray(size: Int): Array<KnowledgeSystemChild?> {
            return arrayOfNulls(size)
        }
    }
}

// 导航
data class Navigation(
    val articles: MutableList<Article>,
    val cid: Int,
    val name: String,
    var isSelected: Boolean
)

// 项目
data class Project(
    val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val visible: Int
)

// 登录数据
data class LoginData(
    val chapterTops: MutableList<String>,
    val collectIds: MutableList<String>,
    val email: String,
    val icon: String,
    val id: Int,
    val password: String,
    val token: String,
    val type: Int,
    val username: String
)

// 注册数据
data class RegisterData(
    var data: LoginData,
    var errorCode: Int,
    var errorMsg: String
)

// 热搜
data class HotSearch(
    val id: Int,
    val link: String,
    val name: String,
    val order: Int,
    val visible: Int
)

const val TYPE_ARTICLE = 0x222
const val TYPE_BANNER = 0x111
