package com.example.capstone.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.capstone.data.models.Item

@Dao
interface ItemsDao {
    @Query("SELECT * FROM items")
    suspend fun getItems(): List<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<Item>)
}