package com.masai.sainath.itunesapi.remote.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "itunes_db")
data class ItunesDbTable(
    @ColumnInfo(name = "artistName")
    val artistName: String,
    @ColumnInfo(name = "artistImageUrl")
    val artistImageUrl: String
) {
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int? = null
}