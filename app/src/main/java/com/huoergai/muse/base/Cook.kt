package com.huoergai.muse.base

import com.huoergai.muse.network.IMAGE_BASE_URL
import com.huoergai.muse.network.YOUTUBE_THUMBNAIL_URL
import com.huoergai.muse.network.YOUTUBE_VIDEO_URL

/**
 * D&T: 2023-10-12 21:51
 * DES:
 */
object Cook {

    fun buildProfileUrl(profilePath: String): String {
        val url = "${IMAGE_BASE_URL}w185${profilePath}"
        return url
    }

    fun buildPosterUrl(posterPath: String): String {
        val url = "${IMAGE_BASE_URL}w342${posterPath}"
        return url
    }

    fun buildBackdropUrl(backdropPath: String): String {
        val url = "${IMAGE_BASE_URL}w780${backdropPath}"
        return url
    }

    fun videoThumbnailUrl(videoKey: String): String {
        return "${YOUTUBE_THUMBNAIL_URL}$videoKey/default.jpg"
    }

    fun videoUrl(videoKey: String): String {
        return "${YOUTUBE_VIDEO_URL}$videoKey"
    }

}