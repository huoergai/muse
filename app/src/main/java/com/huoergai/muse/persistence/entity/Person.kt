package com.huoergai.muse.persistence.entity

import android.os.Parcelable
import com.huoergai.muse.network.model.network.KnownFor
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val id: Int,
    var page: Int,
    val name: String,
    val profile_path: String,
    val known_for: List<KnownFor>,
    val known_for_department: String,
    val popularity: Double
) : Parcelable