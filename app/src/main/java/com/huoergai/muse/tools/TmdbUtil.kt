package com.huoergai.muse.tools

/**
 * D&T: 2023-05-12 17:04
 * DES:
 */
object TmdbUtil {
    init {
        System.loadLibrary("muse")
    }

    external fun hello(): String
    external fun hi(): String
}