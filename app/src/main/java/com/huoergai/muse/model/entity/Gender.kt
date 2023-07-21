package com.huoergai.muse.model.entity

/**
 * D&T: 2023-07-20 17:53
 * DES:
 */
enum class Gender(val g: Int) {
    Unknown(0), // not set/not specified
    Female(1),
    Male(2),
    NonBinary(3)
}