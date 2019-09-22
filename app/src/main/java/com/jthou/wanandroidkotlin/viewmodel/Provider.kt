package com.jthou.wanandroidkotlin.viewmodel

import com.jthou.wanandroidkotlin.repository.*
import com.jthou.wanandroidkotlin.utils.Injection

/**
 *
 *
 * @author jthou
 * @version 1.0.0
 * @date 24-08-2019
 */
object Provider {

    fun mainViewModel() : MainViewModelFactory {
        return MainViewModelFactory(MainRepository(Injection.getDataRepository()))
    }

    fun knowledgeSystemViewModel() : KnowledgeSystemViewModelFactory {
        return KnowledgeSystemViewModelFactory(KnowledgeSystemRepository(Injection.getDataRepository()))
    }

    fun navigationViewModel() : NavigationViewModelFactory {
        return NavigationViewModelFactory(NavigationRepository(Injection.getDataRepository()))
    }

    fun projectViewModel() : ProjectViewModelFactory {
        return ProjectViewModelFactory(ProjectRepository(Injection.getDataRepository()))
    }

    fun settingViewModel() : SettingViewModelFactory {
        return SettingViewModelFactory(SettingRepository(Injection.getDataRepository()))
    }

    fun loginViewModel() : LoginViewModelFactory {
        return LoginViewModelFactory(LoginRepository(Injection.getDataRepository()))
    }

    fun registerViewModel() : RegisterViewModelFactory {
        return RegisterViewModelFactory(RegisterRepository(Injection.getDataRepository()))
    }

    fun favoriteViewModel() : FavoriteViewModelFactory {
        return FavoriteViewModelFactory(FavoriteRepository(Injection.getDataRepository()))
    }

    fun searchViewModel() : SearchViewModelFactory {
        return SearchViewModelFactory(SearchRepository(Injection.getDataRepository()))
    }

    fun searchListViewModel() : SearchListViewModelFactory {
        return SearchListViewModelFactory(SearchListRepository(Injection.getDataRepository()))
    }

    fun knowledgeSystemListViewModel() : KnowledgeSystemListViewModelFactory {
        return KnowledgeSystemListViewModelFactory(KnowledgeSystemListRepository(Injection.getDataRepository()))
    }

}