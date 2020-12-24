package com.salesground.androidtesting.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "media_table")
data class MediaEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "media_primary_key") val mediaId: Long = 0L,
    @ColumnInfo(name = "media_name") val mediaName: String,
    @ColumnInfo(name = "media_type") val mediaType: String
)