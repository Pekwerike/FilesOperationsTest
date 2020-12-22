package com.salesground.androidtesting

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

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
}