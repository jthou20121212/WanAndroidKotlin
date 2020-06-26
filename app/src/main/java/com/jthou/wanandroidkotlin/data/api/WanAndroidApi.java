package com.jthou.wanandroidkotlin.data.api;

import com.jthou.wanandroidkotlin.data.entity.*;
import io.reactivex.Observable;
import retrofit2.http.*;

import java.util.List;

/**
 * Created by user on 2018/5/17.
 */

public interface WanAndroidApi {

    String HOST = "https://www.wanandroid.com/";

    @GET("article/list/{page}/json")
    Observable<AbstractResponse<BaseResponse<Article>>> getArticleList(@Path("page") int page);

    @GET("banner/json")
    Observable<AbstractResponse<List<Banner>>> getBannerList();

    @POST("user/login")
    @FormUrlEncoded
    Observable<AbstractResponse<LoginData>> login(@Field("username") String username, @Field("password") String password);

    @GET("tree/json")
    Observable<AbstractResponse<List<KnowledgeSystem>>> getKnowledgeSystemList();

    @GET("lg/collect/list/{page}/json")
    Observable<AbstractResponse<BaseResponse<Article>>> getFavoriteList(@Path("page") int page);

    @GET("navi/json")
    Observable<AbstractResponse<List<Navigation>>> getNavigationData();

    @GET("project/tree/json")
    Observable<AbstractResponse<List<Project>>> getProjectType();

    @GET("project/list/{page}/json")
    Observable<AbstractResponse<BaseResponse<Article>>> getProjectList(@Path("page") int page, @Query("cid") int cid);

    @GET("hotkey/json")
    Observable<AbstractResponse<List<HotSearch>>> getHotKeyList();

    @POST("article/query/{page}/json")
    Observable<AbstractResponse<BaseResponse<Article>>> getSearchList(@Path("page") int page, @Query("k") String keyword);

    @GET("article/list/{page}/json")
    Observable<AbstractResponse<BaseResponse<Article>>> getKnowledgeSystemArticleList(@Path("page") int page, @Query("cid") int cid);

    @POST("lg/collect/{id}/json")
    Observable<AbstractResponse<String>> favoriteArticle(@Path("id") int articleId);

    @POST("lg/uncollect_originId/{id}/json")
    Observable<AbstractResponse<String>> cancelFavoriteArticle(@Path("id") int articleId);

    @POST("user/register")
    @FormUrlEncoded
    Observable<RegisterData> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);

}
