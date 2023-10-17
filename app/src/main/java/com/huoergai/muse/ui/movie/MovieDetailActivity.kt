package com.huoergai.muse.ui.movie

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.IntentCompat
import androidx.core.view.ViewCompat
import com.bumptech.glide.Glide
import com.huoergai.muse.base.BaseActivity
import com.huoergai.muse.base.GlobalDataStore
import com.huoergai.muse.databinding.ActivityMovieDetailBinding
import com.huoergai.muse.extension.enableTransition
import com.huoergai.muse.model.entity.Movie

/**
 * D&T: 2023-10-16 18:24
 * DES:
 */
class MovieDetailActivity : BaseActivity() {
    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableTransition()

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        IntentCompat.getParcelableExtra(intent, EXTRA_KEY_MOVIE, Movie::class.java)
        val movie = IntentCompat.getParcelableExtra(intent, EXTRA_KEY_MOVIE, Movie::class.java)

        movie?.let {
            ViewCompat.setTransitionName(binding.sivPoster, it.title)

            val posterUrl = GlobalDataStore.buildImageUrl(it.poster_path)
            Glide.with(this)
                .load(posterUrl)
                .into(binding.sivPoster)
        }

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