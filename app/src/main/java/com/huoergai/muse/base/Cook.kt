package com.huoergai.muse.base

import com.huoergai.muse.model.entity.Configuration
import com.huoergai.muse.network.YOUTUBE_THUMBNAIL_URL
import com.huoergai.muse.network.YOUTUBE_VIDEO_URL

/**
 * D&T: 2023-10-12 21:51
 * DES:
 */
object Cook {

    fun buildImageUrl(
        posterPath: String,
        isPoster: Boolean = false,
        config: Configuration = Configuration.defaultConfig
    ): String {
        val posterSizes = config.images.poster_sizes
        val posterSize = if (isPoster) {
            if (posterSizes.contains("w500")) {
                "w500"
            } else if (posterSizes.contains("w342")) {
                "w342"
            } else {
                posterSizes.last()
            }
        } else {
            if (posterSizes.contains("w342")) {
                "w342"
            } else if (posterSizes.contains("w500")) {
                "w500"
            } else {
                posterSizes.last()
            }
        }

        val url = "${config.images.base_url}${posterSize}${posterPath}"
        // Timber.tag("GlobalDataStore").i(url)
        return url
    }

    fun videoThumbnailUrl(videoKey: String): String {
        return "${YOUTUBE_THUMBNAIL_URL}$videoKey/default.jpg"
    }

    fun videoUrl(videoKey: String): String {
        return "${YOUTUBE_VIDEO_URL}$videoKey"
    }

}