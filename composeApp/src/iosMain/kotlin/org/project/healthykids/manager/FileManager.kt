package org.project.healthykids.manager

// iosMain
import com.natighajiyev.domain.network.request.FileRequest
import platform.Foundation.NSData
import platform.Foundation.NSURL
import platform.UIKit.UIDocumentPickerViewController
import platform.UIKit.UIDocumentPickerDelegateProtocol
import kotlinx.cinterop.ObjCAction
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class IOSFilePicker : NSObject(), FileManager, UIDocumentPickerDelegateProtocol {
    private var continuation: (PickedFile?) -> Unit = {}

    override suspend fun pickFile(): FileRequest? = suspendCancellableCoroutine { cont ->
        continuation = { cont.resume(it) }

        val picker = UIDocumentPickerViewController(documentTypes = listOf("public.data"), inMode = 0)
        picker.delegate = this
        val root = UIApplication.sharedApplication.keyWindow?.rootViewController
        root?.presentViewController(picker, animated = true, completion = null)
    }

    @ObjCAction
    fun documentPicker(controller: UIDocumentPickerViewController, url: NSURL) {
        val data = NSData.dataWithContentsOfURL(url)?.toByteArray()

        val title = url.lastPathComponent

        var size: Long? = null
        val path = url.path
        if (path != null) {
            val attrs = NSFileManager.defaultManager.attributesOfItemAtPath(path, null) as NSDictionary?
            size = attrs?.get(NSFileSize) as? Long
        }

        continuation(FileRequest(title, data, size))
    }
}