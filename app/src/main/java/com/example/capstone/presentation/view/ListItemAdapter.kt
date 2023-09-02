package com.example.capstone.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone.databinding.AttributeGroupItemBinding
import com.example.capstone.databinding.HeroItemBinding
import com.example.capstone.presentation.models.AttributeGroup
import com.example.capstone.presentation.models.Hero
import com.example.capstone.presentation.models.ListItem

class ListItemAdapter(
    private val itemsList: List<ListItem>,
    private val onHeroClickListener: OnHeroClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ListItem.Type.TypeHero.ordinal -> {
                val binding =
                    HeroItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HeroViewHolder(binding)
            }

            ListItem.Type.TypeAttributeGroup.ordinal -> {
                val binding = AttributeGroupItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                AttributeGroupViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = itemsList[position]) {
            is Hero -> (holder as HeroViewHolder).bind(item)
            is AttributeGroup -> (holder as AttributeGroupViewHolder).bind(item)
        }
    }

    override fun getItemCount(): Int = itemsList.size

    override fun getItemViewType(position: Int): Int = itemsList[position].getListItemType()

    inner class HeroViewHolder(private val binding: HeroItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(hero: Hero) {
            binding.root.setOnClickListener { onHeroClickListener.onHeroClick(hero) }
            binding.heroName.text = hero.localizedName
            binding.heroAttackType.text = hero.attackType
            val formattedRegen = String.format("+%.1f", hero.baseHealthRegen)
            binding.heroHealthRegen.text = formattedRegen
            val formattedArmor = String.format("%.1f", hero.baseArmor)
            binding.heroBaseArmor.text = formattedArmor
            binding.heroBaseMoveSpeed.text = "${hero.moveSpeed}ms"
            binding.heroWinRate.text = "${hero.proWinRate}%"
        }
    }

    inner class AttributeGroupViewHolder(private val binding: AttributeGroupItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(attributeGroup: AttributeGroup) {
            binding.attributeName.text = attributeGroup.attribute.name.uppercase()
        }
    }

    interface OnHeroClickListener {
        fun onHeroClick(hero: Hero)
    }

}