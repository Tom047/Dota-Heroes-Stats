package com.example.capstone.presentation.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstone.databinding.FragmentHeroesListBinding
import com.example.capstone.presentation.models.Hero
import com.example.capstone.presentation.view.ListItemAdapter
import com.example.capstone.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeroesListFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentHeroesListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeroesListBinding.inflate(layoutInflater)

        setHeroesObserver()

        return binding.root
    }

    private fun setHeroesObserver() {
        viewModel.heroesLiveData.observe(viewLifecycleOwner) { heroes ->
            viewModel.attributeGroupsLiveData.observe(viewLifecycleOwner) { attributeGroups ->
                val listItems = attributeGroups.flatMap { attributeGroup ->
                    listOf(attributeGroup) + heroes.filter { it.primaryAttr == attributeGroup.attribute }
                }

                val adapter = ListItemAdapter(listItems, object : ListItemAdapter.OnHeroClickListener {
                    override fun onHeroClick(hero: Hero) {
                        val action = HeroesListFragmentDirections.actionOpenHeroDetailsFragment(hero.id)
                        findNavController().navigate(action)
                    }
                })
                binding.heroesList.adapter = adapter
            }
        }

        binding.heroesList.layoutManager = LinearLayoutManager(context)
    }
}