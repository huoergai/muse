package com.huoergai.muse.network.model.network

data class PersonDetail(
    val id: Int,
    val name: String,
    val adult: Boolean,
    val birthday: String,
    val deathday: String?,
    val biography: String,
    val profile_path: String?,
    val also_known_as: List<String>,
    val place_of_birth: String,
    val known_for_department: String,
)