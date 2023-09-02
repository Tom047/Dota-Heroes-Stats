package com.example.capstone.presentation.view.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.example.capstone.R
import com.example.capstone.databinding.FragmentHeroDetailsBinding
import com.example.capstone.domain.use_cases.GetPopularItemsForHeroUseCase
import com.example.capstone.presentation.viewmodel.HeroViewModel
import com.example.capstone.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeroDetailsFragment : Fragment() {

    private lateinit var binding: FragmentHeroDetailsBinding
    private val viewModel: HeroViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeroDetailsBinding.inflate(layoutInflater)

        val args: HeroDetailsFragmentArgs by navArgs()
        val heroId = args.heroId

        setObservers(heroId)

        return binding.root
    }

    private fun setObservers(heroId: Int) {
        viewModel.getHero(heroId)

        viewModel.heroLiveData.observe(viewLifecycleOwner) { hero ->
            binding.heroName.text = hero?.localizedName
            binding.heroAttackType.text = hero?.attackType
            binding.heroRoles.text = hero?.roles?.joinToString(", ")

            hero?.imgUrl?.let { url ->
                Glide.with(this)
                    .load(url)
                    .centerCrop()
                    .into(binding.heroImage)
            }
        }

        viewModel.getHeroPopularItems(heroId)

        viewModel.heroPopularItemsLiveData.observe(viewLifecycleOwner) { items ->
            val startGameItems =
                items.filter { it.gameStage == "start_game_items" }.joinToString(", ") { it.itemName }
            val earlyGameItems =
                items.filter { it.gameStage == "early_game_items" }.joinToString(", ") { it.itemName }
            val midGameItems =
                items.filter { it.gameStage == "mid_game_items" }.joinToString(", ") { it.itemName }
            val lateGameItems =
                items.filter { it.gameStage == "late_game_items" }.joinToString(", ") { it.itemName }

            binding.startGameItems.text = startGameItems
            binding.earlyGameItems.text = earlyGameItems
            binding.midGameItems.text = midGameItems
            binding.lateGameItems.text = lateGameItems
        }
    }
}