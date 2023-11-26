package com.huoergai.muse.persistence.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.huoergai.muse.network.model.network.Review
import com.huoergai.muse.network.model.network.Video
import kotlinx.parcelize.Parcelize

@Entity("movie")
@Parcelize
data class Movie(
    @PrimaryKey val id: Int,
    var page: Int,
    val title: String,
    val overview: String,
    val poster_path: String,
    val backdrop_path: String,
    val original_title: String,
    val release_date: String,
    val vote_average: Float,
    var videos: List<Video>?,
    var keywords: List<String>?,
    var reviews: List<Review>?,
    val vote_count: Int,
    val popularity: Double
) : Parcelable