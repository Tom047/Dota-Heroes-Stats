package com.example.capstone.presentation.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.capstone.data.HeroesRepository
import com.example.capstone.domain.use_cases.GetHeroesUseCase
import com.example.capstone.presentation.models.AttributeGroup
import com.example.capstone.presentation.models.Hero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val heroesRepository: HeroesRepository,
    private val sharedPreferences: SharedPreferences
) :
    ViewModel() {

    companion object {
        const val PREF_SORT_BY = "sort_by"
        const val SORT_BY_NAME = "hero_name"
        const val SORT_BY_ATTACK_TYPE = "attack_type"
        const val SORT_BY_REGEN = "health_regen"
        const val SORT_BY_ARMOR = "base_armor"
        const val SORT_BY_SPEED = "movement_speed"
        const val SORT_BY_WINRATE = "winrate"
    }

    val heroesLiveData: MutableLiveData<List<Hero>> = MutableLiveData()
    val attributeGroupsLiveData: LiveData<List<AttributeGroup>> = heroesLiveData.map { heroes ->
        heroes.groupBy { it.primaryAttr }
            .map { (attribute, heroes) -> AttributeGroup(attribute, heroes) }
            .sortedBy { it.attribute.name }
    }
    private val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        if (key == PREF_SORT_BY) {
            loadHeroes()
        }
    }

    init {
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
        loadHeroes()
    }

    fun loadHeroes() {
        viewModelScope.launch {
            val heroes = GetHeroesUseCase(heroesRepository).invoke()
            val sortOption = sharedPreferences.getString(PREF_SORT_BY, SORT_BY_NAME)
            heroesLiveData.value = heroes.sortedWith(compareBy { hero ->
                when (sortOption) {
                    SORT_BY_NAME -> hero.localizedName
                    SORT_BY_ATTACK_TYPE -> hero.attackType
                    SORT_BY_REGEN -> hero.baseHealthRegen
                    SORT_BY_ARMOR -> hero.baseArmor
                    SORT_BY_SPEED -> hero.moveSpeed
                    SORT_BY_WINRATE -> hero.proWinRate
                    else -> hero.localizedName
                }
            })
        }
    }

    override fun onCleared() {
        super.onCleared()
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
    }
}
