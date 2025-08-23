package org.project.healthykids.common

import android.content.Context
import android.widget.Toast

actual class MessageDisplayer(private val ctx: Context) {
    actual fun showToast(msg: String) {
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show()
    }
}