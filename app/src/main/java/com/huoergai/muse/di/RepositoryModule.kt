package com.huoergai.muse.di

import com.huoergai.muse.network.service.MovieService
import com.huoergai.muse.network.service.PeopleService
import com.huoergai.muse.network.service.TvService
import com.huoergai.muse.persistence.MovieDao
import com.huoergai.muse.persistence.PersonDao
import com.huoergai.muse.persistence.TvDao
import com.huoergai.muse.repo.MovieRepo
import com.huoergai.muse.repo.PeopleRepo
import com.huoergai.muse.repo.TvRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * D&T: 2023-10-12 17:10
 * DES:
 */
@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideMovieRepo(movieService: MovieService, movieDao: MovieDao): MovieRepo {
        return MovieRepo(movieService, movieDao)
    }

    @Provides
    fun provideTvRepo(tvService: TvService, tvDao: TvDao): TvRepo {
        return TvRepo(tvService, tvDao)
    }

    @Provides
    fun providePeopleRepo(peopleService: PeopleService, personDao: PersonDao): PeopleRepo {
        return PeopleRepo(peopleService, personDao)
    }

}