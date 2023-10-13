package com.huoergai.muse.base

import com.huoergai.muse.model.entity.Configuration

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
        val posterSize = if (posterSizes.contains("w500")) {
            "w500"
        } else if (posterSizes.contains("w342")) {
            "w342"
        } else {
            posterSizes.last()
        }

        return "${config.images.base_url}${posterSize}${posterPath}"
    }
}