package com.huoergai.muse.network.model.network

import com.huoergai.muse.persistence.entity.Movie

data class MoviesResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int?,
)

