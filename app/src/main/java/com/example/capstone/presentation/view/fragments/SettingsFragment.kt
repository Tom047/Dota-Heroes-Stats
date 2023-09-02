package com.example.capstone.presentation.view.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.capstone.R
import com.example.capstone.databinding.FragmentSettingsBinding
import com.example.capstone.presentation.viewmodel.MainViewModel
import com.example.capstone.presentation.viewmodel.MainViewModel.Companion.PREF_SORT_BY
import com.example.capstone.presentation.viewmodel.MainViewModel.Companion.SORT_BY_ARMOR
import com.example.capstone.presentation.viewmodel.MainViewModel.Companion.SORT_BY_ATTACK_TYPE
import com.example.capstone.presentation.viewmodel.MainViewModel.Companion.SORT_BY_NAME
import com.example.capstone.presentation.viewmodel.MainViewModel.Companion.SORT_BY_REGEN
import com.example.capstone.presentation.viewmodel.MainViewModel.Companion.SORT_BY_SPEED
import com.example.capstone.presentation.viewmodel.MainViewModel.Companion.SORT_BY_WINRATE
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment() {
    @Inject lateinit var sharedPreferences: SharedPreferences
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCheckedRadioButton()
        binding.sortByRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val sortOption = when (checkedId) {
                R.id.hero_name_button -> SORT_BY_NAME
                R.id.attack_type_radio_button -> SORT_BY_ATTACK_TYPE
                R.id.regen_radio_button -> SORT_BY_REGEN
                R.id.armor_radio_button -> SORT_BY_ARMOR
                R.id.movement_speed_radio_button -> SORT_BY_SPEED
                R.id.win_rate_radio_button -> SORT_BY_WINRATE
                else -> SORT_BY_NAME
            }
            sharedPreferences.edit().putString(PREF_SORT_BY, sortOption).apply()
            viewModel.loadHeroes()
        }
    }

    private fun setCheckedRadioButton() {
        val sortOption = sharedPreferences.getString(PREF_SORT_BY, SORT_BY_NAME)
        val radioButtonId = when (sortOption) {
            SORT_BY_NAME -> R.id.hero_name_button
            SORT_BY_ATTACK_TYPE -> R.id.attack_type_radio_button
            SORT_BY_REGEN -> R.id.regen_radio_button
            SORT_BY_ARMOR -> R.id.armor_radio_button
            SORT_BY_SPEED -> R.id.movement_speed_radio_button
            SORT_BY_WINRATE -> R.id.win_rate_radio_button
            else -> R.id.hero_name_button
        }
        binding.sortByRadioGroup.check(radioButtonId)
    }
}
