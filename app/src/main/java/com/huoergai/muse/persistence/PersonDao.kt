package com.huoergai.muse.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.huoergai.muse.persistence.entity.Person

/**
 * D&T: 2023-11-28 10:47
 * DES:
 */
@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg person: Person)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(people: List<Person>)

    @Update
    suspend fun update(person: Person)

    @Query("SELECT * FROM person WHERE id = :personID")
    suspend fun getPerson(personID: Long): Person?

    @Query("SELECT * FROM person WHERE page = :page_")
    suspend fun getPeople(page_: Int = 1): List<Person>
}