package com.huoergai.muse.network.model.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Reviewer(
    val name: String,
    val username: String,
    val rating: Int?,
    val avatar_path: String?,
) : Parcelable