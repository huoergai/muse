package com.huoergai.muse.ui.movie

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
import com.huoergai.muse.base.Cook
import com.huoergai.muse.databinding.ActivityMovieDetailBinding
import com.huoergai.muse.extension.enableTransition
import com.huoergai.muse.model.entity.Movie
import com.huoergai.muse.ui.view.adapter.ReviewRvAdapter
import com.huoergai.muse.ui.view.adapter.VideoRvAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * D&T: 2023-10-16 18:24
 * DES:
 */
@AndroidEntryPoint
class MovieDetailActivity : BaseActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    private val movieVM: MovieDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableTransition()

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = IntentCompat.getParcelableExtra(intent, EXTRA_KEY_MOVIE, Movie::class.java)

        setupActionBar()

        initView()
        initEvent()

        movie?.let {
            ViewCompat.setTransitionName(binding.ivPoster, it.title)

            movieVM.loadData(it.id)

            val posterUrl = Cook.buildImageUrl(it.poster_path, true)
            binding.ivPoster.load(posterUrl) { crossfade(true) }

            binding.mtvTitle.text = it.title
            binding.mtvReleaseDate.text = getString(R.string.release_date_holder, it.release_date)
            binding.ratingBar.rating = it.vote_average / 2f

        }

    }

    private fun initView() {
        val layoutMgr = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val videoAdapter = VideoRvAdapter().apply {
            setItemClickListener {
                val pos = layoutMgr.getPosition(it)
                val video = this.getData(pos)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Cook.videoUrl(video.key)))
                startActivity(intent)
            }
        }

        lifecycleScope.launch {
            movieVM.videos.collect {
                videoAdapter.setData(it)
            }
        }

        binding.rvTrailer.apply {
            layoutManager = layoutMgr
            adapter = videoAdapter
        }


        val reviewAdapter = ReviewRvAdapter()
        lifecycleScope.launch {
            movieVM.reviews.collect {
                reviewAdapter.setData(it)
            }
        }

        binding.rvReview.apply {
            layoutManager = LinearLayoutManager(this@MovieDetailActivity)
            adapter = reviewAdapter
        }
    }

    private fun setupActionBar() {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.outline_arrow_back_24)
            this.title = ""
        }
    }

    private fun initEvent() {
        var favourite = false
        binding.ivFavourite.apply {
            setOnClickListener {
                favourite = !favourite
                if (favourite) {
                    this.setImageResource(R.drawable.baseline_favorite_24)
                } else {
                    this.setImageResource(R.drawable.baseline_favorite_border_24)
                }
            }

        }



        lifecycleScope.launch {
            movieVM.keywords.collect {
                val radius = resources.getDimension(R.dimen.default_corner_radius)
                val sam = ShapeAppearanceModel.builder()
                    .setAllCorners(CornerFamily.ROUNDED, radius)
                    .build()
                it.forEach { kw ->
                    Chip(this@MovieDetailActivity).apply {
                        setTextAppearanceResource(R.style.ChipTextStyle)
                        setChipBackgroundColorResource(R.color.colorPrimary)
                        shapeAppearanceModel = sam
                        isCheckable = false
                        text = kw.name
                        binding.cgTags.addView(this)
                    }
                }
            }
        }

        lifecycleScope.launch {
            movieVM.movieDetail.collect {
                binding.mtvSummary.text = it?.overview
            }
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
        private const val EXTRA_KEY_MOVIE = "detail:key:movie"
        fun start(activity: Activity, view: View, movie: Movie) {
            val intent = Intent(activity, MovieDetailActivity::class.java)
            intent.putExtra(EXTRA_KEY_MOVIE, movie)
            val anim =
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, movie.title)
            activity.startActivity(intent, anim.toBundle())
        }
    }

}