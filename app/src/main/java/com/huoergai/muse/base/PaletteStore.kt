package com.huoergai.muse.base

import androidx.palette.graphics.Palette

/**
 * D&T: 2023-10-30 15:55
 * DES:
 */
object PaletteStore {
    private val store = mutableMapOf<String, Palette>()

    fun get(path: String): Palette? {
        return store[path]
    }

    fun put(path: String, palette: Palette) {
        store[path] = palette
    }

}