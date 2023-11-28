package com.huoergai.muse.network.model.network

import com.huoergai.muse.persistence.entity.Person

data class PeopleResponse(
    val page: Int,
    val results: List<Person>,
    val total_pages: Int,
)