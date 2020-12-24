package com.salesground.androidtesting.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

interface MediaDao {
    @Insert
    fun insertMediaItem(mediaEntity: MediaEntity)

    @Delete
    fun deleteMediaItem(mediaEntity: MediaEntity)

    @Update
    fun updateMediaItem(mediaEntity: MediaEntity)

    @Query("SELECT * FROM media_table WHERE mediaId = :primaryKey")
    fun getMediaItemById(primaryKey: Long)
}