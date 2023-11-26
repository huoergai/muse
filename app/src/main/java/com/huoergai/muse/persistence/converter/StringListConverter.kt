package com.huoergai.muse.persistence.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * D&T: 2023-11-05 10:06
 * DES:
 */
class StringListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(content: String): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson<List<String>>(content, listType)
    }

    @TypeConverter
    fun fromList(list: List<String>?): String {
        return gson.toJson(list)
    }

}