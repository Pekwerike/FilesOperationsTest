package com.salesground.androidtesting.database

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(maxSdk = 28, minSdk = 28)
class MediaDatabaseTest {
    private lateinit var mediaDB: MediaDatabase
    private lateinit var mediaDao: MediaDao


    @Before
    fun getMediaDatabase() {
        val applicationContext = ApplicationProvider.getApplicationContext<Context>()
        mediaDB =
            Room.inMemoryDatabaseBuilder(applicationContext, MediaDatabase::class.java).build()
        mediaDao = mediaDB.mediaDao()
    }

    @Test
    fun insertMedia() {
        CoroutineScope(Dispatchers.IO).launch {
        val mediaItem = MediaEntity(
            mediaName = "video1.mp4",
            mediaType = "mp4",
            mediaId = System.currentTimeMillis()
        )
        var savedMediaItems: List<MediaEntity> = listOf()
            mediaDao.insertMediaItem(mediaItem)
            savedMediaItems = mediaDao.getAllMediaItem()
            assertEquals(savedMediaItems[0].mediaName, "video1.mp4")
            Log.i("Media", savedMediaItems[0].mediaId.toString())
        }
    }
}