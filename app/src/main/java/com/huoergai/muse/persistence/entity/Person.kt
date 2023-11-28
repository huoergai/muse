package com.huoergai.muse.persistence.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.huoergai.muse.network.model.network.KnownFor
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity("person")
data class Person(
    @PrimaryKey val id: Int,
    var page: Int,
    val name: String,
    val profile_path: String?,
    val known_for: List<KnownFor>,
    var biography: String?,
    var birthday: String?,
    var place_of_birth: String?,
) : Parcelable