package com.example.capstone.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstone.data.HeroesRepository
import com.example.capstone.domain.models.PopularHeroItem
import com.example.capstone.domain.use_cases.GetHeroesUseCase
import com.example.capstone.domain.use_cases.GetPopularItemsForHeroUseCase
import com.example.capstone.presentation.models.Hero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(private val heroesRepository: HeroesRepository) :
    ViewModel() {
    val heroLiveData = MutableLiveData<Hero>()
    val heroPopularItemsLiveData: MutableLiveData<List<PopularHeroItem>> = MutableLiveData()

    fun getHero(heroId: Int) {
        viewModelScope.launch {
            val heroes = GetHeroesUseCase(heroesRepository).invoke()
            val hero = heroes.find { it.id == heroId }
            heroLiveData.value = hero!!
        }
    }

    fun getHeroPopularItems(heroId: Int) {
        viewModelScope.launch {
            val heroPopularItems = GetPopularItemsForHeroUseCase(heroesRepository).invoke(heroId)
            heroPopularItemsLiveData.value = heroPopularItems
        }
    }
}