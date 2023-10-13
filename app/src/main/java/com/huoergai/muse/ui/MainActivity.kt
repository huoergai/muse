package com.huoergai.muse.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.huoergai.muse.R
import com.huoergai.muse.base.BaseActivity
import com.huoergai.muse.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainVM: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

        initData()

        initEvent()
    }

    private fun initView() {
        val navView: BottomNavigationView = binding.bnv
        val navController = findNavController(R.id.fcv)
        // val appBarConfig = AppBarConfiguration(setOf(R.id.nav_movie, R.id.nav_tv, R.id.nav_me))
        // setupActionBarWithNavController(navController, appBarConfig)
        navView.setupWithNavController(navController)
    }

    private fun initData() {
        mainVM.loadData()
    }

    private fun initEvent() {
    }

    companion object
}