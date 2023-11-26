package com.huoergai.muse.persistence.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * D&T: 2023-11-05 09:40
 * DES:
 */
class IntListConverter {
    private val gson = Gson()

    @TypeConverter
    fun stringToIntList(content: String): List<Int>? {
        val listType = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson(content, listType)
    }

    @TypeConverter
    fun intListToString(list: List<Int>): String {
        return gson.toJson(list)
    }
}