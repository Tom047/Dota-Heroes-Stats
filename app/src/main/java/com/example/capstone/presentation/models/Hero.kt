package com.example.capstone.presentation.models

data class Hero(
    val id: Int,
    val name: String,
    val localizedName: String,
    val primaryAttr: Attribute,
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
) : ListItem {
    override fun getListItemType(): Int = ListItem.Type.TypeHero.ordinal
}