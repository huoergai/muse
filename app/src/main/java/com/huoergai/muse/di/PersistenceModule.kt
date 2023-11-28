package com.huoergai.muse.di

import android.content.Context
import androidx.room.Room
import com.huoergai.muse.persistence.MovieDao
import com.huoergai.muse.persistence.MuseDB
import com.huoergai.muse.persistence.PersonDao
import com.huoergai.muse.persistence.TvDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * D&T: 2023-11-05 15:45
 * DES:
 */
@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext appContext: Context): MuseDB {
        return Room.databaseBuilder(appContext, MuseDB::class.java, "Muse.db")
            // .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(museDB: MuseDB): MovieDao {
        return museDB.movieDao()
    }

    @Singleton
    @Provides
    fun provideTvDao(museDB: MuseDB): TvDao {
        return museDB.tvDao()
    }

    @Singleton
    @Provides
    fun providePersonDao(museDB: MuseDB): PersonDao {
        return museDB.personDao()
    }
}