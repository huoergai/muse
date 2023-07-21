package com.huoergai.muse.model.network

/**
 * D&T: 2023-07-09 18:26
 * DES:
 *
 * {
 *   "avatar": {
 *     "gravatar": {
 *       "hash": "9575f9686f528a331b49ee1b87924df8"
 *     },
 *     "tmdb": {
 *       "avatar_path": "/jdad3Q0FWNjSmArwWUkD3s2M02W.jpg"
 *     }
 *   },
 *   "id": 13781327,
 *   "iso_639_1": "en",
 *   "iso_3166_1": "NL",
 *   "name": "Frank",
 *   "include_adult": false,
 *   "username": "huoergai"
 * }
 *
 */
data class AccountDetailResponse(
    val avatar: Avatar,
    val id: Long,
    val include_adult: Boolean,
    val iso_3166_1: String,
    val iso_639_1: String,
    val name: String,
    val username: String
)

data class Avatar(
    val gravatar: Gravatar,
    val tmdb: Tmdb
)

data class Gravatar(
    val hash: String
)

data class Tmdb(
    val avatar_path: String
)