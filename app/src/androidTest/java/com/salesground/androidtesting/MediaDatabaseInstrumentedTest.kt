package com.salesground.androidtesting

import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.salesground.androidtesting.database.MediaDao
import com.salesground.androidtesting.database.MediaDatabase
import com.salesground.androidtesting.database.MediaEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MediaDatabaseInstrumentedTest {
    private lateinit var mediaDB: MediaDatabase
    private lateinit var mediaDao: MediaDao


    @Before
    fun getMediaDatabase() {
        val applicationContext = InstrumentationRegistry.getInstrumentation().context
        mediaDB = Room.inMemoryDatabaseBuilder(applicationContext, MediaDatabase::class.java)
                .build()
        mediaDao = mediaDB.mediaDao()
    }

    @After
    fun closeMediaDatabase(){
        mediaDB.close()
    }

    @Test
    fun insertMedia()  {
        CoroutineScope(Dispatchers.IO).launch {
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
            mediaDao.insertMediaItem(
                MediaEntity(
                    mediaName = "video3.mp4",
                    mediaType = "mp4"
                )
            )

            assertEquals(mediaDao.getMediaItemById(1).mediaName, "video1.mp4")
            assertEquals(mediaDao.getMediaItemById(2).mediaName, "video2.mp4")
            assertNotEquals(mediaDao.getMediaItemById(3).mediaName, "video2.mp4")

        }
    }

    @Test
    fun deleteMediaFromDBTest() {
        CoroutineScope(Dispatchers.IO).launch {
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
    }

    @Test
    fun getAllMediaItemTest()  {
        CoroutineScope(Dispatchers.IO).launch {
            val video1 = MediaEntity(mediaName = "video1", mediaType = "mp4")
            val video2 = MediaEntity(mediaName = "video2", mediaType = "mp4")
            val image1 = MediaEntity(mediaName = "image1", mediaType = "jpeg")

            mediaDao.insertMediaItem(video1)
            mediaDao.insertMediaItem(video2)
            mediaDao.insertMediaItem(image1)

            assertEquals(mediaDao.getAllMediaItem().size, 3)
            assert(mediaDao.getAllMediaItem().isNotEmpty())
        }
    }

    @Test
    fun insertMultipleMediaItemAndGetAllMediaItemTest() {
        CoroutineScope(Dispatchers.IO).launch {
            val video1 = MediaEntity(mediaName = "video1", mediaType = "mp4")
            val video2 = MediaEntity(mediaName = "video2", mediaType = "mp4")
            val image1 = MediaEntity(mediaName = "image1", mediaType = "jpeg")

            mediaDao.insertMultipleMediaItems(listOf(video1, video2, image1))
            assertEquals(mediaDao.getAllMediaItem().size, 3)
        }
    }

    @Test
    fun updateMediaItemTest() {
        CoroutineScope(Dispatchers.IO).launch {
            val video1 = MediaEntity(mediaType = "mp4", mediaName = "video1.mp4")
            mediaDao.insertMediaItem(video1)
            val videoQueried = mediaDao.getMediaItemById(1)
            val updatedVideo = MediaEntity(
                mediaId = videoQueried.mediaId,
                mediaName = "image30", mediaType = "jpg"
            )
            mediaDao.updateMediaItem(updatedVideo)
            assertEquals(mediaDao.getMediaItemById(1).mediaName, "image30")
            assertNotEquals(mediaDao.getMediaItemById(1).mediaType, "mp4")
        }
    }

    @Test
    fun insertSameEntityTwiceTest() {
        CoroutineScope(Dispatchers.IO).launch {
            val video = MediaEntity(mediaId = 1, mediaName = "video", mediaType = "mp4")
            mediaDao.insertMediaItem(video)
            mediaDao.insertMediaItem(video)
            assertEquals(mediaDao.getAllMediaItem().size, 1)
        }
    }
}