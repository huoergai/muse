package com.huoergai.muse.persistence.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.huoergai.muse.network.model.network.Keyword
import com.huoergai.muse.network.model.network.Review
import com.huoergai.muse.network.model.network.Video
import kotlinx.parcelize.Parcelize

/**
 * D&T: 2023-10-12 16:04
 * DES:
 */
@Entity("tv")
@Parcelize
data class Tv(
    @PrimaryKey val id: Long,
    var page: Int,
    val name: String,
    val poster_path: String,
    val backdrop_path: String?,
    val first_air_date: String,
    val overview: String,
    val vote_average: Float,
    val vote_count: Int,
    val popularity: Float,
    var videos: List<Video>?,
    var keywords: List<Keyword>?,
    var reviews: List<Review>?
) : Parcelable
