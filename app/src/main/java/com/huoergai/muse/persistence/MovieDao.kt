package com.huoergai.muse.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.huoergai.muse.persistence.entity.Movie

/**
 * D&T: 2023-11-05 11:55
 * DES:
 */
@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg movies: Movie)

    @Update
    suspend fun update(movie: Movie)

    @Query("SELECT * FROM movie WHERE id = :id_")
    suspend fun getMovie(id_: Int): Movie

    @Query("SELECT * FROM movie WHERE page = :page_")
    suspend fun getMovies(page_: Int = 1): List<Movie>

}