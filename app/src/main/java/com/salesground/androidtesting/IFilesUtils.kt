package com.salesground.androidtesting

import java.io.File

interface IFilesUtils {

    fun createFile(fileName: String): File
    fun deleteFile(file: File)
    fun renameFile(file: File, newFileName: String): File
    fun getMainFolder(): File
    fun deleteMainDirectory(): List<File>
}

