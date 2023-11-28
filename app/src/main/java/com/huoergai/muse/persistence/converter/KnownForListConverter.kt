package com.huoergai.muse.persistence.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.huoergai.muse.network.model.network.KnownFor

/**
 * D&T: 2023-11-28 11:44
 * DES:
 */
class KnownForListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(content: String): List<KnownFor>? {
        val listType = object : TypeToken<List<KnownFor>>() {}.type
        return gson.fromJson(content, listType)
    }

    @TypeConverter
    fun fromKnownFor(knownForList: List<KnownFor>?): String {
        return gson.toJson(knownForList)
    }

}