package com.example.moviesapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LastRefreshTimeDao {

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insert(lastRefreshTime: LastRefreshTimeEntity)

   @Query("SELECT * FROM LastRefreshTimeEntity WHERE id = 1")
   suspend fun getLastRefreshTime(): LastRefreshTimeEntity
}