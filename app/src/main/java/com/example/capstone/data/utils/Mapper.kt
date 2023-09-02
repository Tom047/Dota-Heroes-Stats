package com.example.capstone.data.utils

import com.example.capstone.data.models.Hero

private const val BASE_URL = "https://api.opendota.com"

fun Hero.toDomain() = com.example.capstone.domain.models.Hero(
    id = this.id,
    name = this.name,
    localizedName = this.localizedName,
    primaryAttr = this.primaryAttr,
    attackType = this.attackType,
    roles = this.roles,
    imgUrl = BASE_URL + this.img.dropLast(1),
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
    proWinRate = ((this.proWin.toDouble() / this.proPick.toDouble()) * 100).toInt()
)