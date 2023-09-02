package com.example.capstone.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.capstone.data.models.HeroItem

@Dao
interface HeroItemsDao {
    @Query("SELECT * FROM hero_items WHERE heroId = :heroId AND quantity >= 10")
    suspend fun getHeroItems(heroId: Int): List<HeroItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(heroItems: List<HeroItem>)
}