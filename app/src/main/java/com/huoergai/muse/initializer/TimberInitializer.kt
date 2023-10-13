package com.huoergai.muse.initializer

import android.content.Context
import androidx.startup.Initializer
import com.huoergai.muse.extension.isDebug
import timber.log.Timber

/**
 * D&T: 2023-05-15 11:20
 * DES:
 */
@Suppress("unused")
class TimberInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        if (context.isDebug()) {
            Timber.plant(Timber.DebugTree())
            Timber.d("Timber is initialized.")
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()

}