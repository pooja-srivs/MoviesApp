package com.example.moviesapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LastRefreshTimeEntity (@PrimaryKey(autoGenerate = true) val id: Int = 1 ,val lastRefreshTime: Long? = System.currentTimeMillis())