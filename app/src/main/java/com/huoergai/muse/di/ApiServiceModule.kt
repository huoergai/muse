package com.huoergai.muse.di

import com.huoergai.muse.network.service.AccountService
import com.huoergai.muse.network.service.ConfigService
import com.huoergai.muse.network.service.MovieService
import com.huoergai.muse.network.service.TvService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import retrofit2.Retrofit

/**
 * D&T: 2023-10-12 15:32
 * DES:
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
object ApiServiceModule {

    @Provides
    fun provideAccountService(retrofit: Retrofit): AccountService {
        return retrofit.create(AccountService::class.java)
    }

    @Provides
    fun provideConfigService(retrofit: Retrofit): ConfigService {
        return retrofit.create(ConfigService::class.java)
    }

    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }

    @Provides
    fun provideTvService(retrofit: Retrofit): TvService {
        return retrofit.create(TvService::class.java)
    }

}