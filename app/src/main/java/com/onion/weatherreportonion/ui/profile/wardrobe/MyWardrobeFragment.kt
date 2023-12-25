package com.onion.weatherreportonion.ui.profile.wardrobe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.onion.weatherreportonion.R
import com.onion.weatherreportonion.databinding.FragmentMyWardrobeBinding

class MyWardrobeFragment: Fragment() {

    private lateinit var binding: FragmentMyWardrobeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_wardrobe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMyWardrobeBinding.bind(view)

        initElements()
    }

    private fun initElements() {
        binding.addThingFAB.setOnClickListener {
            addFragment(R.id.fragment_container, AddThingFragment())
        }
        binding.filtersLayout.setOnClickListener {
            addFragment(R.id.fragment_container, FiltersFragment())
        }
        binding.searchView.setOnClickListener { }
        binding.itemsWardrobeRecycler
    }

    private fun addFragment(container: Int, fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(container, fragment)
            .addToBackStack(null)
            .commit()
    }
}