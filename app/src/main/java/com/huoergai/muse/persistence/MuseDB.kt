package com.huoergai.muse.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.huoergai.muse.persistence.converter.IntListConverter
import com.huoergai.muse.persistence.converter.KeywordListConverter
import com.huoergai.muse.persistence.converter.ReviewListConverter
import com.huoergai.muse.persistence.converter.ReviewerConverter
import com.huoergai.muse.persistence.converter.StringListConverter
import com.huoergai.muse.persistence.converter.VideoListConverter
import com.huoergai.muse.persistence.entity.Movie
import com.huoergai.muse.persistence.entity.Tv

/**
 * D&T: 2023-11-04 17:43
 * DES:
 */
@Database(version = 1, entities = [Movie::class, Tv::class])
@TypeConverters(
    IntListConverter::class,
    StringListConverter::class,
    VideoListConverter::class,
    KeywordListConverter::class,
    ReviewListConverter::class,
    ReviewerConverter::class
)
abstract class MuseDB : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    abstract fun tvDao(): TvDao

}