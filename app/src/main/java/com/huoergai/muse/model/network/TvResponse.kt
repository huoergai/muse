package com.huoergai.muse.model.network

import com.huoergai.muse.model.entity.Tv

data class TvResponse(
    val page: Int,
    val results: List<Tv>,
    val total_pages: Int,
    val total_results: Int
)