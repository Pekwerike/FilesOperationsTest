package com.salesground.androidtesting

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun createFile(context: Context, fileName: String?) : File?{
        fileName?.let {
            val folder = File(context.getExternalFilesDir(null), fileName)
            if(!folder.exists()) folder.mkdirs()
            return File(folder, fileName)
        }
        return null
    }
}