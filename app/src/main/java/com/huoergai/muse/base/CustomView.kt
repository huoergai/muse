package com.huoergai.muse.base

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * D&T: 2023-09-07 21:41
 * DES:
 */
abstract class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    protected val Int.dp: Int get() = (this * resources.displayMetrics.density + 0.5f).toInt()
    protected val Float.dp: Float get() = this * resources.displayMetrics.density
}