package com.masai.sainath.itunesapi.remote.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.masai.sainath.itunesapi.remote.db.AppDao
import com.masai.sainath.itunesapi.remote.db.ItunesDbTable

@Database(entities = [ItunesDbTable::class], version = 1)
abstract class ItunesRoomDatabase : RoomDatabase() {

    abstract fun getResponseFromDao(): AppDao

}