package com.example.capstone.data.utils

import android.content.Context
import androidx.room.Room
import com.example.capstone.data.room.dao.HeroesDao
import com.example.capstone.data.room.HeroesDatabase
import com.example.capstone.data.room.dao.HeroItemsDao
import com.example.capstone.data.room.dao.ItemsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): HeroesDatabase {
        return Room.databaseBuilder(
            appContext,
            HeroesDatabase::class.java,
            "heroes.db"
        ).build()
    }

    @Provides
    fun provideHeroesDao(database: HeroesDatabase): HeroesDao {
        return database.heroesDao()
    }

    @Provides
    fun provideItemsDao(database: HeroesDatabase): ItemsDao {
        return database.itemsDao()
    }

    @Provides
    fun provideHeroItemsDao(database: HeroesDatabase): HeroItemsDao {
        return database.heroItemsDao()
    }
}
