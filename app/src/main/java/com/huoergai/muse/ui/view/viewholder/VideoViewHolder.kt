package com.huoergai.muse.ui.view.viewholder

import android.view.View
import coil.load
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import com.huoergai.muse.R
import com.huoergai.muse.base.BaseViewHolder
import com.huoergai.muse.base.Cook
import com.huoergai.muse.model.network.Video

/**
 * D&T: 2023-10-25 10:44
 * DES:
 */
class VideoViewHolder(itemView: View) : BaseViewHolder(itemView) {
    private val mtvTitle = itemView.findViewById<MaterialTextView>(R.id.mtv_title)
    private val sivThumbnail = itemView.findViewById<ShapeableImageView>(R.id.siv_thumbnail)

    fun bind(video: Video) {
        mtvTitle.text = video.name
        sivThumbnail.load(Cook.videoThumbnailUrl(video.key)) {
            crossfade(true)
        }
    }

}