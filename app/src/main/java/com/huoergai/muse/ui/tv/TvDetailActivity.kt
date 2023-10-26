package com.huoergai.muse.ui.tv

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.IntentCompat
import androidx.core.view.ViewCompat
import coil.load
import com.huoergai.muse.R
import com.huoergai.muse.base.BaseActivity
import com.huoergai.muse.base.Cook
import com.huoergai.muse.databinding.ActivityTvDetailBinding
import com.huoergai.muse.extension.enableTransition
import com.huoergai.muse.model.entity.Tv
import dagger.hilt.android.AndroidEntryPoint

/**
 * D&T: 2023-10-26 16:08
 * DES:
 */
@AndroidEntryPoint
class TvDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityTvDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableTransition()

        binding = ActivityTvDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()

        val tv = IntentCompat.getParcelableExtra(intent, EXTRA_KEY_TV, Tv::class.java)
        tv?.let {
            ViewCompat.setTransitionName(findViewById(android.R.id.content), it.name)

            binding.sivPoster.load(Cook.buildBackdropUrl(it.backdrop_path))
            binding.mtvTitle.text = it.name
            binding.mtvReleaseDate.text = getString(R.string.first_air_date, it.first_air_date)
            binding.ratingBar.rating = it.vote_average / 2f
        }

        var isFavourite = false
        binding.sivFavourite.setOnClickListener {
            isFavourite = !isFavourite
            val resID =
                if (isFavourite) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24
            binding.sivFavourite.setImageResource(resID)
        }
    }

    private fun setupActionBar() {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.outline_arrow_back_24)
            title = ""
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