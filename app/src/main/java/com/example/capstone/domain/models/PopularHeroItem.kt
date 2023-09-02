package com.example.capstone.domain.models

data class PopularHeroItem(
    val heroId: Int,
    val itemId: Int,
    val itemName: String,
    val quantity: Int,
    val gameStage: String
)