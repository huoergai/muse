package com.huoergai.muse.network

import android.util.Log
import coil.util.Logger
import timber.log.Timber

/**
 * D&T: 2023-10-24 15:39
 * DES:
 */
class CoinLogger(override var level: Int) : Logger {
    override fun log(tag: String, priority: Int, message: String?, throwable: Throwable?) {
        if (priority >= level) {
            when (priority) {
                Log.VERBOSE -> Timber.tag(tag).v(throwable, message)
                Log.DEBUG -> Timber.tag(tag).d(throwable, message)
                Log.INFO -> Timber.tag(tag).i(throwable, message)
                Log.WARN -> Timber.tag(tag).w(throwable, message)
                Log.ERROR -> Timber.tag(tag).e(throwable, message)
                Log.ASSERT -> Timber.tag(tag).wtf(throwable, message)
                else -> {}
            }
        }
    }
}