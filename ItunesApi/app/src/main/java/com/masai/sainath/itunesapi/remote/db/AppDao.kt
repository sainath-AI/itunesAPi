package com.masai.sainath.itunesapi.remote.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDataFromAPI(result: ItunesDbTable)

    @Query("select * from itunes_db")
    fun getResponseFromDb(): LiveData<ItunesDbTable>

    @Query("delete from itunes_db")
    fun deleteAllDataFromDB()

}