package org.project.healthykids.common

import platform.UIKit.UIAlertController
import platform.UIKit.UIAlertAction
import platform.UIKit.UIAlertActionStyleDefault
import platform.UIKit.UIAlertControllerStyleAlert
import platform.UIKit.UIApplication


actual class MessageDisplayer {
    actual fun showToast(msg: String) {
        val alert = UIAlertController.alertControllerWithTitle(
            title = null,
            message = msg,
            preferredStyle = UIAlertControllerStyleAlert
        )
        alert.addAction(UIAlertAction.actionWithTitle("OK", UIAlertActionStyleDefault, null))
        UIApplication.sharedApplication.keyWindow?.rootViewController
            ?.presentViewController(alert, animated = true, completion = null)
    }
}
