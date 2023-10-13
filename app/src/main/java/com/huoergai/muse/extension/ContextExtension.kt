package com.huoergai.muse.extension

import android.content.Context
import android.content.pm.ApplicationInfo
import android.widget.Toast
import androidx.annotation.StringRes

/*
 * D&T: 2023-09-12 12:40
 * DES:
 */

fun Context.toast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}

fun Context.toast(@StringRes resId: Int) {
    Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show()
}

fun Context.isDebug(): Boolean {
    val debugFlag = applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE
    return debugFlag != 0
}