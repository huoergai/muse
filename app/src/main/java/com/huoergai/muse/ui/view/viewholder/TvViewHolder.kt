package com.huoergai.muse.ui.view.viewholder

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatRatingBar
import coil.load
import coil.transform.RoundedCornersTransformation
import com.google.android.material.textview.MaterialTextView
import com.huoergai.muse.R
import com.huoergai.muse.base.BaseViewHolder
import com.huoergai.muse.base.Cook
import com.huoergai.muse.model.entity.Tv

/**
 * D&T: 2023-10-25 15:33
 * DES:
 */
class TvViewHolder(itemView: View) : BaseViewHolder(itemView) {
    private val sivPoster = itemView.findViewById<AppCompatImageView>(R.id.iv_poster)
    private val mtvName = itemView.findViewById<MaterialTextView>(R.id.mtv_name)
    private val mtvDate = itemView.findViewById<MaterialTextView>(R.id.mtv_date)
    private val ratingBar = itemView.findViewById<AppCompatRatingBar>(R.id.rating_bar)

    fun bind(tv: Tv) {
        mtvName.text = tv.name
        mtvDate.text = tv.first_air_date
        ratingBar.rating = tv.vote_average / 2f

        sivPoster.load(Cook.buildPosterUrl(tv.poster_path)) {
            transformations(RoundedCornersTransformation(sivPoster.resources.getDimension(R.dimen.corner_radius_small)))
        }
    }

}