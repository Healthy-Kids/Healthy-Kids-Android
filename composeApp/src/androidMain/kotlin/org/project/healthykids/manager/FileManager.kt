package org.project.healthykids.manager

import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.natighajiyev.domain.network.request.FileRequest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine

// androidMain
class AndroidFilePicker(
    private val activity: ComponentActivity?,
) : FileManager {

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun pickFile(): FileRequest? = suspendCancellableCoroutine { cont ->
        val launcher = activity?.activityResultRegistry?.register(
            "filePicker",
            ActivityResultContracts.GetContent()
        ) { uri ->
            if (uri != null) {
                val pair = getFileInfo(activity, uri)

                val title = pair.first ?: return@register
                val size = pair.second

                val bytes = activity.contentResolver.openInputStream(uri)?.readBytes()
                    ?: return@register

                val file = FileRequest(title, bytes, size)

                cont.resume(file, null)
            } else {
                cont.resume(null, null)
            }
        }

        launcher?.launch("*/*")
    }

    private fun getFileInfo(activity: ComponentActivity?, uri: Uri): Pair<String?, Long?> {
        var name: String? = null
        var size: Long? = null

        if (uri.scheme == "content") {
            val cursor = activity?.contentResolver?.query(uri, null, null, null, null)
            cursor?.use {
                val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                val sizeIndex = it.getColumnIndex(OpenableColumns.SIZE)

                if (it.moveToFirst()) {
                    if (nameIndex >= 0) name = it.getString(nameIndex)
                    if (sizeIndex >= 0) size = it.getLong(sizeIndex)
                }
            }
        }

        if (name == null) name = uri.lastPathSegment
        return name to size
    }
}
