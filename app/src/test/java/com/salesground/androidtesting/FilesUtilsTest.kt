package com.salesground.androidtesting

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.io.File

@RunWith(AndroidJUnit4::class)
@Config(maxSdk = 28, minSdk = 28)
class FilesUtilsTest {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val filesUtil = FilesUtils(context = context)

    @Test
    fun createFile() {
        val videoFile = filesUtil.createFile("Video1.mp4")
        Assert.assertEquals(videoFile.name, "Video1.mp4")
    }

    @Test
    fun deleteFile() {
        val videoFile = filesUtil.createFile("Video2.mp4")
        filesUtil.deleteFile(videoFile)
        Assert.assertEquals(false, videoFile.exists())
    }

    @Test
    fun renameFile() {
        val initialVideo = filesUtil.createFile("Video3.mp4")
        val newVideo = filesUtil.renameFile(initialVideo, "Video4.mp4")
        Assert.assertEquals(newVideo.name, "Video4.mp4")
        Assert.assertEquals(false, initialVideo.exists())
    }

    @Test
    fun getMainFolder() {
        val mainFolder = filesUtil.getMainFolder();
        val imageFile = filesUtil.createFile("image1.jpg")
        Assert.assertEquals(true, mainFolder.isDirectory)
    }

    @Test
    fun deleteMainFolder(){
        filesUtil.createFile("video1.mp4")
        filesUtil.createFile("video2.mp4")
        filesUtil.createFile("video3.mp4")
        val filesInMainDirectory = filesUtil.deleteMainDirectory()
        val mainDirectory = filesUtil.getMainFolder()
        Assert.assertEquals("video1.mp4", filesInMainDirectory[0].name)
        Assert.assertEquals("video2.mp4", filesInMainDirectory[1].name)
        Assert.assertArrayEquals(arrayOf<File>(),  mainDirectory.listFiles())
    }
}