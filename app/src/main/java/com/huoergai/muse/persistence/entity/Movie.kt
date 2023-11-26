package com.huoergai.muse.persistence.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.huoergai.muse.network.model.network.Keyword
import com.huoergai.muse.network.model.network.Review
import com.huoergai.muse.network.model.network.Video
import kotlinx.parcelize.Parcelize

@Entity("movie")
@Parcelize
data class Movie(
    @PrimaryKey val id: Long,
    var page: Int?,
    val title: String,
    val overview: String,
    val poster_path: String,
    val backdrop_path: String?,
    val release_date: String,
    val vote_average: Float,
    val vote_count: Int,
    val popularity: Double,
    var videos: List<Video>?,
    var keywords: List<Keyword>?,
    var reviews: List<Review>?
) : Parcelable