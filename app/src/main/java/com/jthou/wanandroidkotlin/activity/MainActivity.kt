package com.jthou.wanandroidkotlin.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.forEach
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.jthou.wanandroidkotlin.R
import com.jthou.wanandroidkotlin.databinding.ActivityMainBinding
import com.jthou.wanandroidkotlin.event.LoginEvent
import com.jthou.wanandroidkotlin.event.RegisterEvent
import com.jthou.wanandroidkotlin.event.SearchEvent
import com.jthou.wanandroidkotlin.utils.Constant
import com.jthou.wanandroidkotlin.utils.StatusBarUtils
import com.jthou.wanandroidkotlin.utils.startActivityWithAnimator
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    private lateinit var mDrawerListener: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupViews()
    }

    private fun setupViews() {
        setSupportActionBar(mBinding.toolbar)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        StatusBarUtils.setStatusColor(window, ContextCompat.getColor(this, R.color.colorPrimary), 1f)

        mDrawerListener = object : ActionBarDrawerToggle(
            this,
            mBinding.drawerLayout,
            mBinding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ) {}
        mBinding.drawerLayout.addDrawerListener(mDrawerListener)
        mDrawerListener.syncState()

        mBinding.navigationView.apply {
            menu.forEach { it ->
                it.setOnMenuItemClickListener { menuItem ->
                    mBinding.drawerLayout.closeDrawers()
                    when (menuItem.itemId) {
                        R.id.menu_login -> {
                            findNavController(R.id.fragment_container).navigate(R.id.navigation_fragment_login)
                        }
                        R.id.menu_favorite -> {
                            findNavController(R.id.fragment_container).navigate(R.id.navigation_fragment_favorite)
                        }
                        R.id.menu_setting -> {
                            findNavController(R.id.fragment_container).navigate(R.id.navigation_fragment_setting)
                        }
                        R.id.menu_about -> {
                            findNavController(R.id.fragment_container).navigate(R.id.navigation_fragment_about)
                        }
                        R.id.menu_logout -> {

                        }
                    }
                    menu.forEach { it.isChecked = false }
                    menu.findItem(it.itemId).isChecked = true
                    mBinding.bottomNavigationView.visibility = View.GONE
                    false
                }
            }
        }

        mBinding.bottomNavigationView.setOnNavigationItemSelectedListener {
            if (!it.isChecked) {
                it.isChecked = true
                when (it.itemId) {
                    R.id.tab_home_page -> {
                        findNavController(R.id.fragment_container).navigate(R.id.action_main)
                    }
                    R.id.tab_knowledge_hierarchy -> {
                        findNavController(R.id.fragment_container).navigate(R.id.action_knowledge_system)
                    }
                    R.id.tab_navigation -> {
                        findNavController(R.id.fragment_container).navigate(R.id.action_navigation)
                    }
                    R.id.tab_project -> {
                        findNavController(R.id.fragment_container).navigate(R.id.action_project)
                    }
                }
            }

            false
        }

        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        fragment.navController.currentDestination?.let {
            if (it.id != R.id.navigation_fragment_main) {
                mBinding.navigationView.menu.forEach { menuItem ->
                    menuItem.isChecked = menuItem.itemId == R.id.menu_setting
                }
                mBinding.bottomNavigationView.visibility = View.GONE
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when {
            Navigation.findNavController(
                this,
                R.id.fragment_container
            ).currentDestination?.id == R.id.navigation_fragment_search -> return super.onOptionsItemSelected(
                item
            )
            item.itemId == R.id.id_search -> {
                mBinding.bottomNavigationView.visibility = View.GONE
                findNavController(R.id.fragment_container).navigate(R.id.action_search)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @Subscribe
    fun toRegister(event: RegisterEvent) {
        findNavController(R.id.fragment_container).navigate(R.id.action_register)
    }

    @Subscribe
    fun onLoginSuccess(event: LoginEvent) {
        mBinding.navigationView.menu.findItem(R.id.menu_login).title = event.loginData.username
        findNavController(R.id.fragment_container).navigateUp()
    }

    @Subscribe
    fun showSearchList(event: SearchEvent) {
        val intent = Intent(this, SearchListActivity::class.java)
        intent.putExtra(Constant.ArgumentKey.IT_KEYWORD, event.keyword)
        startActivityWithAnimator(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fragment_container).navigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        mBinding.drawerLayout.removeDrawerListener(mDrawerListener)
        super.onDestroy()
    }

    override fun onBackPressed() {
        val id = findNavController(R.id.fragment_container).currentDestination?.id
        when {
            mBinding.drawerLayout.isDrawerOpen(GravityCompat.START) -> {
                mBinding.drawerLayout.closeDrawer(GravityCompat.START)
                mBinding.bottomNavigationView.visibility = View.VISIBLE
            }
            id != R.id.navigation_fragment_main -> {
                findNavController(R.id.fragment_container).navigate(R.id.action_main)
                mBinding.navigationView.menu.forEach { it.isChecked = false }
                mBinding.bottomNavigationView.visibility = View.VISIBLE
                mBinding.bottomNavigationView.selectedItemId = R.id.tab_home_page
            }
            else -> super.onBackPressed()
        }
    }

}
