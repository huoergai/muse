package com.huoergai.muse.network.model.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Reviewer(
    val avatar_path: String?,
    val name: String,
    val rating: Int,
    val username: String
) : Parcelable