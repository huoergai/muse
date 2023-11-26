package com.huoergai.muse.persistence.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.huoergai.muse.network.model.network.Keyword

/**
 * D&T: 2023-11-05 11:42
 * DES:
 */
class KeywordListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(content: String): List<Keyword>? {
        val listType = object : TypeToken<List<Keyword>>() {}.type
        return gson.fromJson(content, listType)
    }

    @TypeConverter
    fun fromList(keywords: List<Keyword>?): String {
        return gson.toJson(keywords)
    }

}