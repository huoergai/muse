package com.huoergai.muse.model.network

import com.huoergai.muse.model.entity.Movie

data class MoviesResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)

