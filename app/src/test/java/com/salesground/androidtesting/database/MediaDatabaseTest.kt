package com.salesground.androidtesting.database

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineContext
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.io.IOException

@RunWith(AndroidJUnit4::class)
@Config(maxSdk = 28, minSdk = 28)
class MediaDatabaseTest {
    private lateinit var mediaDB: MediaDatabase
    private lateinit var mediaDao: MediaDao


    @Before
    fun getMediaDatabase() {
        val applicationContext = ApplicationProvider.getApplicationContext<Context>()
        mediaDB =
            Room.inMemoryDatabaseBuilder(applicationContext, MediaDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        mediaDao = mediaDB.mediaDao()
    }

    @Test
    fun insertMedia() {
        mediaDao.insertMediaItem(
            MediaEntity(
                mediaName = "video1.mp4",
                mediaType = "mp4"
            )
        )
        mediaDao.insertMediaItem(
            MediaEntity(
                mediaName = "video2.mp4",
                mediaType = "mp4"
            )
        )
        mediaDao.insertMediaItem(MediaEntity(
            mediaName = "video3.mp4",
            mediaType = "mp4"))

        assertEquals(mediaDao.getMediaItemById(1).mediaName, "video1.mp4")
        assertEquals(mediaDao.getMediaItemById(2).mediaName, "video2.mp4")
        assertNotEquals(mediaDao.getMediaItemById(3).mediaName, "video2.mp4")
    }

    @Test
    fun deleteMediaFromDBTest(){
        mediaDao.insertMediaItem(
            MediaEntity(
                mediaName = "video2.mp4",
                mediaType = "mp4"
            )
        )
        val video2 = mediaDao.getMediaItemById(1)
        mediaDao.deleteMediaItem(video2)
        val requestForDeletedVideo = mediaDao.getMediaItemById(1)
        assertEquals(requestForDeletedVideo, null)
    }

    @Test
    fun getAllMediaItemTest(){
        val video1 = MediaEntity(mediaName = "video1", mediaType = "mp4")
        val video2 = MediaEntity(mediaName = "video2", mediaType = "mp4")
        val image1 = MediaEntity(mediaName = "image1", mediaType = "jpeg")

        mediaDao.insertMediaItem(video1)
        mediaDao.insertMediaItem(video2)
        mediaDao.insertMediaItem(image1)

        assertEquals(mediaDao.getAllMediaItem().size, 3 )
    }

    @Test
    fun insertMultipleMediaItemTest(){
        val video1 = MediaEntity(mediaName = "video1", mediaType = "mp4")
        val video2 = MediaEntity(mediaName = "video2", mediaType = "mp4")
        val image1 = MediaEntity(mediaName = "image1", mediaType = "jpeg")

        mediaDao.insertMultipleMediaItems(listOf(video1, video2, image1))
        assertEquals(mediaDao.getAllMediaItem().size, 3)
    }


}