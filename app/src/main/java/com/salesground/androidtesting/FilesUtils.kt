package com.salesground.androidtesting

import android.content.Context
import java.io.File


class FilesUtils(private val context: Context) : IFilesUtils {

    override fun createFile(fileName: String): File {
        val folder = getMainFolder()
        val newFile = File(folder, fileName)
        if(!newFile.exists()) newFile.createNewFile()
        return newFile
    }

    override fun deleteFile(file: File) {
        file.delete()
    }

    override fun renameFile(file: File, newFileName: String): File {
        val folder = getMainFolder()
        val newFile = File(folder, newFileName)
        file.renameTo(newFile)
        return newFile
    }

    override fun getMainFolder(): File {
        val folder = File(context.getExternalFilesDir(null), "MainFolder")
        if (!folder.exists()) folder.mkdirs()
        return folder
    }

    override fun deleteMainDirectory(): List<File> {
        val mainFolder = getMainFolder()
        var filesInMainFolder: List<File> = listOf()
        mainFolder.listFiles()?.sortedBy {
            it.lastModified()
        }?.toList()?.let {
            filesInMainFolder = it
        }
        mainFolder.deleteRecursively()
        return filesInMainFolder
    }
}