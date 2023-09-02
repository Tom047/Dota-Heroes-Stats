package com.example.capstone.presentation.models

interface ListItem {
    enum class Type(value: Int) {
        TypeHero(0),
        TypeAttributeGroup(1)
    }
    fun getListItemType(): Int
}