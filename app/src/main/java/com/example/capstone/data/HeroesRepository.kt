package com.example.capstone.data

import com.example.capstone.data.models.HeroItem
import com.example.capstone.data.models.Item
import com.example.capstone.data.utils.toDomain
import com.example.capstone.domain.models.Hero
import javax.inject.Inject

class HeroesRepository @Inject constructor(private val dataSource: HeroesRemoteDataSource) {

    suspend fun getHeroes(): List<Hero> {
        return try {
            dataSource.getHeroes().map { it.toDomain() }
        } catch (e: Exception) {
            dataSource.getHeroesFromDb().map { it.toDomain() }
        }
    }

    suspend fun getItems(): List<Item> {
        return try {
            dataSource.getItems()
        } catch (e: Exception) {
            dataSource.getItemsFromDb()
        }
    }

    suspend fun getItemPopularityForHero(heroId: Int): List<HeroItem> {
        return try {
            dataSource.getHeroItems(heroId)
        } catch (e: Exception) {
            dataSource.getHeroItemsFromDb(heroId)
        }
    }
}