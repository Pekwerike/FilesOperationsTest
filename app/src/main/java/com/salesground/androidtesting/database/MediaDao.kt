package com.salesground.androidtesting.database

import androidx.room.*

@Dao
interface MediaDao {
    @Insert
    fun insertMediaItem(mediaEntity: MediaEntity)

    @Delete
    fun deleteMediaItem(mediaEntity: MediaEntity)

    @Update
    fun updateMediaItem(mediaEntity: MediaEntity)

    @Query("SELECT * FROM media_table WHERE media_primary_key = :primaryKey")
    fun getMediaItemById(primaryKey: Long) : MediaEntity

    @Query("SELECT * FROM media_table")
    fun getAllMediaItem() : List<MediaEntity>


}