package org.project.healthykids.common

import androidx.activity.ComponentActivity
import java.lang.ref.WeakReference

object ActivityProvider {
    private var activityRef: WeakReference<ComponentActivity>? = null

    fun set(activity: ComponentActivity) {
        activityRef = WeakReference(activity)
    }

    fun get(): ComponentActivity? = activityRef?.get()
}
