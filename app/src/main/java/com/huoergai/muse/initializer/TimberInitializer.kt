package com.huoergai.muse.initializer

import android.content.Context
import android.content.pm.ApplicationInfo
import androidx.startup.Initializer
import timber.log.Timber

/**
 * D&T: 2023-05-15 11:20
 * DES:
 */
@Suppress("unused")
class TimberInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        val debugFlag = context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE
        if (debugFlag != 0) {
            Timber.plant(Timber.DebugTree())
            Timber.d("Timber is initialized.")
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()

}