package com.example.capstone.domain.use_cases

import com.example.capstone.data.HeroesRepository
import com.example.capstone.domain.utils.toPresentation
import com.example.capstone.presentation.models.Hero

class GetHeroesUseCase(private val heroesRepository: HeroesRepository) {

    suspend operator fun invoke(): List<Hero> {
        return heroesRepository.getHeroes().map { it.toPresentation() }
    }
}