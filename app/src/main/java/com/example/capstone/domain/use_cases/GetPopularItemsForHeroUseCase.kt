package com.example.capstone.domain.use_cases

import com.example.capstone.data.HeroesRepository
import com.example.capstone.domain.models.PopularHeroItem

class GetPopularItemsForHeroUseCase(private val heroesRepository: HeroesRepository) {
    suspend operator fun invoke(heroId: Int): List<PopularHeroItem> {
        val items = heroesRepository.getItems()
        val heroItems = heroesRepository.getItemPopularityForHero(heroId)

        if (heroItems.isNotEmpty()) {
            val popularItemsForHero = heroItems.map { heroItem ->
                val item = items.find { it.id == heroItem.itemId }
                PopularHeroItem(
                    heroId = heroItem.heroId,
                    itemId = heroItem.itemId,
                    itemName = item?.name ?: "Unknown",
                    quantity = heroItem.quantity,
                    gameStage = heroItem.gameStage
                )
            }

            return popularItemsForHero
        } else {
            return emptyList()
        }
    }
}