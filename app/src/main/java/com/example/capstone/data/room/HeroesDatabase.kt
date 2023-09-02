package com.example.capstone.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.capstone.data.models.Hero
import com.example.capstone.data.models.HeroItem
import com.example.capstone.data.models.Item
import com.example.capstone.data.room.dao.HeroItemsDao
import com.example.capstone.data.room.dao.HeroesDao
import com.example.capstone.data.room.dao.ItemsDao

@Database(entities = [Hero::class, HeroItem::class, Item::class], version = 1, exportSchema = false)
@TypeConverters(RolesConverter::class)
abstract class HeroesDatabase : RoomDatabase() {
    abstract fun heroesDao(): HeroesDao
    abstract fun itemsDao(): ItemsDao
    abstract fun heroItemsDao(): HeroItemsDao
}
