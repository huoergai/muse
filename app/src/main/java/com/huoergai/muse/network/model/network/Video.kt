package com.huoergai.muse.network.model.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Video(
    val id: String,
    val key: String,
    val name: String,
    val site: String,
) : Parcelable