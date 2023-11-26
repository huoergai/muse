package com.huoergai.muse.network.model.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Review(
    val author: String,
    val author_details: Reviewer,
    val content: String,
    val created_at: String,
    val id: String,
    val updated_at: String?,
    val url: String
) : Parcelable