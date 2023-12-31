package com.huoergai.muse.ui.view.viewholder

import android.graphics.Color
import android.view.View
import android.widget.RatingBar
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import coil.load
import com.google.android.material.textview.MaterialTextView
import com.huoergai.muse.R
import com.huoergai.muse.base.BaseViewHolder
import com.huoergai.muse.base.PaletteStore
import com.huoergai.muse.network.Api
import com.huoergai.muse.persistence.entity.Movie

/**
 * D&T: 2023-10-25 10:43
 * DES:
 */
class MovieViewHolder(itemView: View) : BaseViewHolder(itemView) {
    private val llc = itemView.findViewById<LinearLayoutCompat>(R.id.ll_bg)
    private val ivPoster = itemView.findViewById<AppCompatImageView>(R.id.iv_poster)
    private val mtvTitle = itemView.findViewById<MaterialTextView>(R.id.mtv_title)
    private val ratingBar = itemView.findViewById<RatingBar>(R.id.rating_bar)

    fun bind(movie: Movie) {
        mtvTitle.text = movie.title
        ratingBar.rating = movie.vote_average / 2f

        ivPoster.load(Api.buildPosterUrl(movie.poster_path)) {
            allowHardware(false)
            crossfade(true)
            listener(onSuccess = { _, result ->
                val palette = PaletteStore.get(movie.poster_path)
                if (palette == null) {
                    Palette.Builder(result.drawable.toBitmap()).generate { p ->
                        val dvs = p?.darkVibrantSwatch
                        if (dvs != null) {
                            PaletteStore.put(movie.poster_path, p)
                            llc.setBackgroundColor(dvs.rgb)
                        }
                    }
                } else {
                    llc.setBackgroundColor(palette.darkVibrantSwatch?.rgb ?: Color.YELLOW)
                }

            })
        }
    }
}