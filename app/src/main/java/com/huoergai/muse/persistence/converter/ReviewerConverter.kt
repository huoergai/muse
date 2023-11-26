package com.huoergai.muse.persistence.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.huoergai.muse.network.model.network.Reviewer

/**
 * D&T: 2023-11-05 11:51
 * DES:
 */
class ReviewerConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(content: String): Reviewer? {
        return gson.fromJson(content, Reviewer::class.java)
    }

    @TypeConverter
    fun fromReview(reviewer: Reviewer?): String {
        return gson.toJson(reviewer)
    }

}