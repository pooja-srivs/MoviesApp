package com.example.moviesapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class, LastRefreshTimeEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase(){
    abstract fun remoteResponseDao(): RemoteResponseDao
    abstract fun lastRefreshTimeDao(): LastRefreshTimeDao
}