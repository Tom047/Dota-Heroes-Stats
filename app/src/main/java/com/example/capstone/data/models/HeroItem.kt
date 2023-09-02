package com.example.capstone.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hero_items", primaryKeys = ["heroId", "itemId"])
data class HeroItem(
    val heroId: Int,
    val itemId: Int,
    val quantity: Int,
    val gameStage: String
)