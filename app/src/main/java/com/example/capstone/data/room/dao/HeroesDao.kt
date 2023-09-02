package com.example.capstone.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.capstone.data.models.Hero

@Dao
interface HeroesDao {
    @Query("SELECT * FROM heroes")
    suspend fun getHeroes(): List<Hero>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(heroes: List<Hero>)
}
