package com.huoergai.muse.network.model.network

import com.huoergai.muse.persistence.entity.Tv

data class TvResponse(
    val page: Int,
    val results: List<Tv>,
    val total_pages: Int,
)