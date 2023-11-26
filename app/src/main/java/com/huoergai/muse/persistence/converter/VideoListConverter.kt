package com.huoergai.muse.persistence.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.huoergai.muse.network.model.network.Video

/**
 * D&T: 2023-11-05 11:37
 * DES:
 */
class VideoListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(content: String): List<Video>? {
        val listType = object : TypeToken<List<Video>>() {}.type
        return gson.fromJson(content, listType)
    }

    @TypeConverter
    fun fromVideo(videos: List<Video>?): String {
        return gson.toJson(videos)
    }
}