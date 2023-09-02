package com.example.capstone.presentation.models

data class AttributeGroup(
    val attribute: Attribute,
    val heroes: List<Hero>
) : ListItem {
    override fun getListItemType(): Int = ListItem.Type.TypeAttributeGroup.ordinal
}