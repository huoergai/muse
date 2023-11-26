package com.huoergai.muse.network

/**
 * D&T: 2023-10-12 21:51
 * DES:
 */

const val TMDB_URL = "https://api.themoviedb.org"
const val TMDB_V3 = "$TMDB_URL/3/"
const val TMDB_V4 = "$TMDB_URL/4/"

const val accountID = 1231

const val IMAGE_BASE_URL = "http://image.tmdb.org/t/p/"
const val IMAGE_SECURE_BASE_URL = "https://image.tmdb.org/t/p/"

const val YOUTUBE_VIDEO_URL = "https://www.youtube.com/watch?v="
const val YOUTUBE_THUMBNAIL_URL = "https://img.youtube.com/vi/"

// image url = base_url + file_size + file_path
// http://image.tmdb.org/t/p/w780/jdad3Q0FWNjSmArwWUkD3s2M02W.jpg

// language: language code-country code(ISO 639-1,ISO 3166-1); zh-CN en-US

object Api {

    fun buildProfileUrl(profilePath: String): String {
        val url = "${IMAGE_BASE_URL}w185${profilePath}"
        return url
    }

    fun buildPosterUrl(posterPath: String): String {
        val url = "${IMAGE_BASE_URL}w342${posterPath}"
        return url
    }

    fun buildBackdropUrl(backdropPath: String?): String {
        val url = "${IMAGE_BASE_URL}w780${backdropPath}"
        return url
    }

    fun videoThumbnailUrl(videoKey: String): String {
        return "$YOUTUBE_THUMBNAIL_URL$videoKey/default.jpg"
    }

    fun videoUrl(videoKey: String): String {
        return "$YOUTUBE_VIDEO_URL$videoKey"
    }

}