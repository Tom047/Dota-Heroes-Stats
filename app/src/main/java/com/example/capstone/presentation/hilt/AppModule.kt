package com.example.capstone.presentation.hilt

import android.content.Context
import android.content.SharedPreferences
import com.example.capstone.data.HeroesRemoteDataSource
import com.example.capstone.data.HeroesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideHeroesRepository(dataSource: HeroesRemoteDataSource): HeroesRepository {
        return HeroesRepository(dataSource)
    }
}
