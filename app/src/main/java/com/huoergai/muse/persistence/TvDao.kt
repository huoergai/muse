package com.huoergai.muse.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.huoergai.muse.persistence.entity.Tv

/**
 * D&T: 2023-11-05 11:56
 * DES:
 */
@Dao
interface TvDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg tv: Tv)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Tv>)

    @Update
    suspend fun update(tv: Tv)

    @Query("SELECT * FROM tv WHERE id = :id_")
    suspend fun getTv(id_: Long): Tv?

    @Query("SELECT * FROM tv WHERE page = :page_")
    suspend fun getTvs(page_: Int = 1): List<Tv>
}