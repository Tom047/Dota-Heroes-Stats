package com.example.capstone.domain.models

data class Hero(
    val id: Int,
    val name: String,
    val localizedName: String,
    val primaryAttr: String,
    val attackType: String,
    val roles: List<String>,
    val imgUrl: String,
    val baseHealthRegen: Double,
    val baseManaRegen: Double,
    val baseArmor: Double,
    val baseStr: Int,
    val baseAgi: Int,
    val baseInt: Int,
    val strGain: Double,
    val agiGain: Double,
    val intGain: Double,
    val attackRange: Int,
    val moveSpeed: Int,
    val proWinRate: Int
)