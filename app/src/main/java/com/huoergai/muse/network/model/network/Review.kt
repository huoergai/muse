package com.huoergai.muse.network.model.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Review(
    val id: String,
    val author: String,
    val author_details: Reviewer,
    val content: String,
    val created_at: String,
    val updated_at: String?,
) : Parcelable