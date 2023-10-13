package com.huoergai.muse.di

import com.huoergai.muse.network.service.ConfigService
import com.huoergai.muse.network.service.MovieService
import com.huoergai.muse.network.service.TvService
import com.huoergai.muse.repo.ConfigRepo
import com.huoergai.muse.repo.MovieRepo
import com.huoergai.muse.repo.TvRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers

/**
 * D&T: 2023-10-12 17:10
 * DES:
 */
@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideConfigRepo(configService: ConfigService): ConfigRepo {
        return ConfigRepo(configService, Dispatchers.IO)
    }

    @Provides
    fun provideMovieRepo(movieService: MovieService): MovieRepo {
        return MovieRepo(movieService, Dispatchers.IO)
    }

    @Provides
    fun provideTvRepo(tvService: TvService): TvRepo {
        return TvRepo(tvService, Dispatchers.IO)
    }

}