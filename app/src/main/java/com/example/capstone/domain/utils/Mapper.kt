package com.example.capstone.domain.utils

import com.example.capstone.domain.models.Hero

fun Hero.toPresentation() = com.example.capstone.presentation.models.Hero(
    id = this.id,
    name = this.name,
    localizedName = this.localizedName,
    primaryAttr = when (this.primaryAttr) {
        "agi" -> com.example.capstone.presentation.models.Attribute.AGILITY
        "int" -> com.example.capstone.presentation.models.Attribute.INTELLIGENCE
        "str" -> com.example.capstone.presentation.models.Attribute.STRENGTH
        else -> com.example.capstone.presentation.models.Attribute.UNIVERSAL
    },
    attackType = this.attackType,
    roles = this.roles,
    imgUrl = this.imgUrl,
    baseHealthRegen = this.baseHealthRegen,
    baseManaRegen = this.baseManaRegen,
    baseArmor = this.baseArmor,
    baseStr = this.baseStr,
    baseAgi = this.baseAgi,
    baseInt = this.baseInt,
    strGain = this.strGain,
    agiGain = this.agiGain,
    intGain = this.intGain,
    attackRange = this.attackRange,
    moveSpeed = this.moveSpeed,
    proWinRate = this.proWinRate
)