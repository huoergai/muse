package com.huoergai.muse.model

/**
 * D&T: 2023-07-20 17:32
 * DES:
 */
enum class MovieListType(val type: String) {
    NowPlaying("now_playing"),
    Popular("popular"),
    TopRated("top_rated"),
    Upcoming("upcoming")
}