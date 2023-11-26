package com.huoergai.muse.network.model.network

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Keyword(
    val id: Int,
    val name: String
) : Parcelable