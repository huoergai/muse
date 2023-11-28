package com.huoergai.muse.network.model.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * 代表作
 */
@Parcelize
data class KnownFor(
    val id: Int,
    val name: String?,
    val title: String,
    val overview: String,
    val poster_path: String,
    val backdrop_path: String?,
    val release_date: String,
    val vote_average: Float,
    val vote_count: Int
) : Parcelable