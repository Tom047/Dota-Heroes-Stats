package com.example.capstone.data


import com.example.capstone.data.retrofit.OpenDotaApi
import com.example.capstone.data.room.dao.HeroesDao
import com.example.capstone.data.models.Hero
import com.example.capstone.data.models.HeroItem
import com.example.capstone.data.models.Item
import com.example.capstone.data.room.dao.HeroItemsDao
import com.example.capstone.data.room.dao.ItemsDao
import javax.inject.Inject

class HeroesRemoteDataSource @Inject constructor(
    private val heroesDao: HeroesDao,
    private val itemsDao: ItemsDao,
    private val heroItemsDao: HeroItemsDao
) {

    suspend fun getHeroes(): List<Hero> {
        val heroesFromApi = OpenDotaApi.retrofitService.getHeroes()
        heroesDao.insertAll(heroesFromApi)
        return heroesFromApi
    }

    suspend fun getHeroesFromDb(): List<Hero> {
        return heroesDao.getHeroes()
    }


    suspend fun getItems(): List<Item> {
        val itemIdsFromApi = OpenDotaApi.retrofitService.getItemIds()
        val items = itemIdsFromApi.map { Item(it.key, it.value) }
        itemsDao.insertAll(items)
        return items
    }

    suspend fun getItemsFromDb(): List<Item> {
        return itemsDao.getItems()
    }


    suspend fun getHeroItems(heroId: Int): List<HeroItem> {
        val itemPopularityFromApi = OpenDotaApi.retrofitService.getItemPopularity(heroId)
        val heroItems = itemPopularityFromApi.map { (gameStage, itemIdQuantityMap) ->
            itemIdQuantityMap.filter { it.value >= 10 }.map {
                HeroItem(
                    heroId = heroId,
                    itemId = it.key,
                    quantity = it.value,
                    gameStage = gameStage
                )
            }
        }.flatten()
        heroItemsDao.insertAll(heroItems)
        return heroItems
    }

    suspend fun getHeroItemsFromDb(heroId: Int): List<HeroItem> {
        return heroItemsDao.getHeroItems(heroId).filter { it.quantity >= 10 }
    }
}