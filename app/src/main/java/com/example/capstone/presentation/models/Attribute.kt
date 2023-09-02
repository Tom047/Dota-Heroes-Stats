package com.example.capstone.presentation.models

enum class Attribute {
    AGILITY,
    INTELLIGENCE,
    STRENGTH,
    UNIVERSAL;

    override fun toString(): String {
        return when (this) {
            AGILITY -> "Agility"
            INTELLIGENCE -> "Intelligence"
            STRENGTH -> "Strength"
            UNIVERSAL -> "Universal"
        }
    }
}