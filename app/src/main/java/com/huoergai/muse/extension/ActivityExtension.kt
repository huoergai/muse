package com.huoergai.muse.extension

import android.app.Activity
import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.TransitionSet
import android.view.Window

/**
 * D&T: 2023-10-05 21:09
 * DES:
 */

fun Activity.enableTransition() {
    window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
}

fun Activity.shareTransition() {
    TransitionSet().apply {
        addTransition(ChangeBounds())
        addTransition(ChangeImageTransform())

        window.sharedElementEnterTransition = this
        window.sharedElementExitTransition = this
    }
}