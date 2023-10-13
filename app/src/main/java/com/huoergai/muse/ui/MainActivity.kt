package com.huoergai.muse.ui

import android.os.Bundle
import android.transition.Scene
import android.transition.TransitionManager
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.huoergai.muse.R
import com.huoergai.muse.base.BaseActivity
import com.huoergai.muse.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainVM: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

        initEvent()
    }

    private fun initEvent() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    mainVM.isLoading.collect { isLoading ->
                        if (isLoading) {
                            binding.progressBar.show()
                        } else {
                            binding.progressBar.hide()
                        }
                    }
                }

                mainVM.loadData()
            }
        }
    }

    private fun initView() {
        val sceneRoot = binding.flRoot
        val sceneMovie = Scene(sceneRoot, findViewById<View>(R.id.container))
        val sceneTv = Scene.getSceneForLayout(sceneRoot, R.layout.scene_tv, this)
        val sceneMe = Scene.getSceneForLayout(sceneRoot, R.layout.scene_me, this)

        binding.bnv.setOnItemSelectedListener {
            when (it.order) {
                0 -> TransitionManager.go(sceneMovie)
                1 -> TransitionManager.go(sceneTv)
                2 -> TransitionManager.go(sceneMe)
                else -> {}
            }
            true
        }

        val movieRv = binding.flRoot.findViewById<RecyclerView>(R.id.rv_movie)
        val movieRvAdapter = RvAdapter(mainVM.movieList.value)
        movieRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = movieRvAdapter
        }

    }

    companion object
}