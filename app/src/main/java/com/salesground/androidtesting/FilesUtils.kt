package com.salesground.androidtesting

import android.content.Context
import java.io.File

class FilesUtils(private val context: Context) {

    fun createFile(fileName: String): File {
        val folder = getMainFolder()
        val newFile = File(folder, fileName)
        newFile.createNewFile()
        return newFile
    }

    fun deleteFile(file: File) {
        file.delete()
    }

    fun renameFile(file: File, newFileName: String): File {
        val folder = getMainFolder()
        val newFile = File(folder, newFileName)
        file.renameTo(newFile)
        return newFile
    }

    fun getMainFolder(): File {
        val folder = File(context.getExternalFilesDir(null), "MainFolder")
        if (!folder.exists()) folder.mkdirs()
        return folder
    }

    fun deleteMainDirectory(): List<File> {
        val mainFolder = getMainFolder()
        var filesInMainFolder: List<File> = listOf()
        mainFolder.listFiles()?.sortedByDescending {
            it.lastModified()
        }?.toList()?.let {
            filesInMainFolder = it
        }
        mainFolder.deleteRecursively()
        return filesInMainFolder
    }
}