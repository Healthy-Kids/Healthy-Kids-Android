package org.project.healthykids.manager

import com.natighajiyev.domain.network.request.FileRequest

interface FileManager {
    suspend fun pickFile(): FileRequest?
}

