package com.huoergai.muse.persistence.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.huoergai.muse.network.model.network.Review

/**
 * D&T: 2023-11-05 11:45
 * DES:
 */
class ReviewListConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromString(content: String): List<Review>? {
        val listType = object : TypeToken<List<Review>>() {}.type
        return gson.fromJson(content, listType)
    }

    @TypeConverter
    fun fromList(reviews: List<Review>?): String {
        return gson.toJson(reviews)
    }

}