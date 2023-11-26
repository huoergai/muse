package com.huoergai.muse.ui.tv

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.IntentCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.chip.Chip
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel
import com.huoergai.muse.R
import com.huoergai.muse.base.BaseActivity
import com.huoergai.muse.databinding.ActivityTvDetailBinding
import com.huoergai.muse.extension.enableTransition
import com.huoergai.muse.network.Api
import com.huoergai.muse.persistence.entity.Tv
import com.huoergai.muse.ui.view.adapter.ReviewRvAdapter
import com.huoergai.muse.ui.view.adapter.VideoRvAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * D&T: 2023-10-26 16:08
 * DES:
 */
@AndroidEntryPoint
class TvDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityTvDetailBinding
    private val tvVM by viewModels<TvDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableTransition()

        binding = ActivityTvDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initEvent()

        val tv = IntentCompat.getParcelableExtra(intent, EXTRA_KEY_TV, Tv::class.java)
        setupActionBar(tv?.name ?: "")

        tv?.let {
            ViewCompat.setTransitionName(findViewById(android.R.id.content), it.name)

            tvVM.loadData(it.id)

            binding.sivPoster.load(Api.buildBackdropUrl(it.backdrop_path))
            binding.mtvTitle.text = it.name
            binding.mtvReleaseDate.text = getString(R.string.first_air_date, it.first_air_date)
            binding.ratingBar.rating = it.vote_average / 2f
            binding.mtvSummary.text = it.overview
        }
    }

    private fun initView() {
        val layoutMgr = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val videoAdapter = VideoRvAdapter().apply {
            setItemClickListener {
                val pos = layoutMgr.getPosition(it)
                val video = this.getData(pos)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Api.videoUrl(video.key)))
                startActivity(intent)
            }
        }

        lifecycleScope.launch {
            tvVM.videos.collect {
                videoAdapter.setData(it)
            }
        }

        binding.rvTrailer.apply {
            layoutManager = layoutMgr
            adapter = videoAdapter
        }

        val reviewAdapter = ReviewRvAdapter()
        lifecycleScope.launch {
            tvVM.tvReview.collect {
                reviewAdapter.setData(it)
            }
        }

        binding.rvReview.apply {
            layoutManager = LinearLayoutManager(this@TvDetailActivity)
            adapter = reviewAdapter
        }
    }

    private fun initEvent() {
        var isFavourite = false
        binding.sivFavourite.setOnClickListener {
            isFavourite = !isFavourite
            val resID =
                if (isFavourite) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24
            binding.sivFavourite.setImageResource(resID)
        }

        lifecycleScope.launch {
            tvVM.keywords.collect { list ->
                val radius = resources.getDimension(R.dimen.default_corner_radius)
                val sam = ShapeAppearanceModel.builder()
                    .setAllCorners(CornerFamily.ROUNDED, radius)
                    .build()
                list.forEach {
                    Chip(this@TvDetailActivity).apply {
                        text = it.name
                        setTextAppearanceResource(R.style.ChipTextStyle)
                        setChipBackgroundColorResource(R.color.colorPrimary)
                        shapeAppearanceModel = sam
                        binding.cgTags.addView(this)
                    }
                }
            }
        }
    }

    private fun setupActionBar(title: String) {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.outline_arrow_back_24)
            this.title = title
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val EXTRA_KEY_TV = "_extra:key:tv"
        fun start(activity: Activity, share: View, tv: Tv) {
            val intent = Intent(activity, TvDetailActivity::class.java).apply {
                putExtra(EXTRA_KEY_TV, tv)
            }
            val anim =
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity, share, tv.name)
            activity.startActivity(intent, anim.toBundle())
        }
    }

}