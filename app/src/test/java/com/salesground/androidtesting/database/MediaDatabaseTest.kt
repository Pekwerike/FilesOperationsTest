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

    @ExperimentalCoroutinesApi
    @Test
    @Throws(IOException::class)
    fun insertMedia() {
        val mediaItem = MediaEntity(
            mediaName = "video1.mp4",
            mediaType = "mp4")
        var savedMediaItems: List<MediaEntity>
        mediaDao.insertMediaItem(mediaItem)
        savedMediaItems = mediaDao.getAllMediaItem()
        print(savedMediaItems[0].mediaId.toString() + ", name: ${savedMediaItems[0].mediaName}" )
        assertEquals(savedMediaItems[0].mediaName, "video1.mp4")


    }
/*
    @Test
    fun getAllMediaPrimaryKeys(){
        CoroutineScope(Dispatchers.IO).launch {
           val mediaPrimaryKeys : List<Long> = mediaDao.getAllMediaPrimaryKeys()

        }
    }*/
}