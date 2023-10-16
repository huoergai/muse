package com.huoergai.muse.base

import com.huoergai.muse.model.entity.Configuration
import timber.log.Timber

/**
 * D&T: 2023-10-12 21:51
 * DES:
 */
object GlobalDataStore {

    fun buildImageUrl(
        posterPath: String,
        config: Configuration = Configuration.defaultConfig
    ): String {
        val posterSizes = config.images.poster_sizes
        val posterSize = if (posterSizes.contains("w342")) {
            "w342"
        } else if (posterSizes.contains("w500")) {
            "w500"
        } else {
            posterSizes.last()
        }

        val url = "${config.images.base_url}${posterSize}${posterPath}"
        Timber.tag("GlobalDataStore").i(url)

        return url
    }
}