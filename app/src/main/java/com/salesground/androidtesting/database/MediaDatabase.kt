package com.salesground.androidtesting.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(MediaEntity::class), version = 1, exportSchema = false)
abstract class MediaDatabase : RoomDatabase() {
    abstract fun mediaDao(): MediaDao

    companion object {
        private val MEDIA_DATABASE_INSTANCE: MediaDatabase? = null

        fun getMediaDatabase(applicationContext: Context): MediaDatabase {
            var instance = MEDIA_DATABASE_INSTANCE
            if (instance != null) {
                return instance
            }
            synchronized(this) {
                return Room.databaseBuilder(
                    applicationContext,
                    MediaDatabase::class.java,
                    "MediaDB"
                ).build()

            }
        }
    }
}