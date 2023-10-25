package com.huoergai.muse

import android.app.Application
import android.util.Log
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.huoergai.muse.extension.isDebug
import com.huoergai.muse.network.CoinLogger
import dagger.hilt.android.HiltAndroidApp

/**
 * D&T: 2023-05-13 10:43
 * DES:
 */
@HiltAndroidApp
class MuseApp : Application(), ImageLoaderFactory {

    override fun newImageLoader(): ImageLoader {

        val level = if (isDebug()) Log.DEBUG else Log.WARN

        return ImageLoader.Builder(this)
            .logger(CoinLogger(level))
            .crossfade(true)
            .build()
    }

}