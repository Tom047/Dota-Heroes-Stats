package com.example.capstone.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.capstone.data.room.RolesConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "heroes")
data class Hero(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("localized_name")
    val localizedName: String,
    @SerializedName("primary_attr")
    val primaryAttr: String,
    @SerializedName("attack_type")
    val attackType: String,
    @SerializedName("roles")
    @TypeConverters(RolesConverter::class)
    val roles: List<String>,
    @SerializedName("legs")
    val legs: Int,
    @SerializedName("img")
    val img: String,
    @SerializedName("base_health_regen")
    val baseHealthRegen: Double,
    @SerializedName("base_mana_regen")
    val baseManaRegen: Double,
    @SerializedName("base_armor")
    val baseArmor: Double,
    @SerializedName("base_str")
    val baseStr: Int,
    @SerializedName("base_agi")
    val baseAgi: Int,
    @SerializedName("base_int")
    val baseInt: Int,
    @SerializedName("str_gain")
    val strGain: Double,
    @SerializedName("agi_gain")
    val agiGain: Double,
    @SerializedName("int_gain")
    val intGain: Double,
    @SerializedName("attack_range")
    val attackRange: Int,
    @SerializedName("move_speed")
    val moveSpeed: Int,
    @SerializedName("pro_win")
    val proWin: Int,
    @SerializedName("pro_pick")
    val proPick: Int
)
