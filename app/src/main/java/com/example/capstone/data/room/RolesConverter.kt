package com.example.capstone.data.room

import androidx.room.TypeConverter

class RolesConverter {
    @TypeConverter
    fun fromRolesList(roles: List<String>): String {
        return roles.joinToString(separator = ",")
    }

    @TypeConverter
    fun toRolesList(roles: String): List<String> {
        return roles.split(",")
    }
}
