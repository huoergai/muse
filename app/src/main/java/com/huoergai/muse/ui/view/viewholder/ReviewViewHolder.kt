package com.huoergai.muse.ui.view.viewholder

import android.view.View
import androidx.appcompat.widget.AppCompatRatingBar
import coil.load
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import com.huoergai.muse.R
import com.huoergai.muse.base.BaseViewHolder
import com.huoergai.muse.model.network.Review
import com.huoergai.muse.network.Api
import com.ms.square.android.expandabletextview.ExpandableTextView

/**
 * D&T: 2023-10-25 10:49
 * DES:
 */
class ReviewViewHolder(itemView: View) : BaseViewHolder(itemView) {
    private val sivAvatar = itemView.findViewById<ShapeableImageView>(R.id.siv_avatar)
    private val mtvAvatar = itemView.findViewById<MaterialTextView>(R.id.mtv_avatar)
    private val mtvName = itemView.findViewById<MaterialTextView>(R.id.mtv_name)
    private val mtvDate = itemView.findViewById<MaterialTextView>(R.id.mtv_date)
    private val ratingBar = itemView.findViewById<AppCompatRatingBar>(R.id.rating_bar)
    private val expandableReview = itemView.findViewById<ExpandableTextView>(R.id.etv_review)

    fun bind(review: Review) {
        mtvName.text = review.author
        mtvDate.text = review.created_at.substring(0, 10)
        ratingBar.rating = review.author_details.rating / 2f
        expandableReview.text = review.content

        val avatarPath = review.author_details.avatar_path
        if (avatarPath == null) {
            mtvAvatar.apply {
                visibility = View.VISIBLE
                text = review.author.first().uppercase()
            }
        } else {
            mtvAvatar.visibility = View.INVISIBLE
            sivAvatar.load(Api.buildPosterUrl(avatarPath))
        }

    }

}