package com.huoergai.muse.network.model.network

data class ReviewResponse(
    val id: Long,
    val results: List<Review>,
    val total_pages: Int,
)