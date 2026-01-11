package com.onion.weatherreportonion.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.onion.weatherreportonion.R
import com.onion.weatherreportonion.databinding.FragmentMyProfileBinding
import com.onion.weatherreportonion.ui.profile.wardrobe.MyWardrobeFragment
import com.onion.weatherreportonion.ui.settings.ChangeSettingsFragment

class MyProfileFragment: Fragment() {

    private lateinit var binding: FragmentMyProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMyProfileBinding.bind(view)

        initElements()
    }

    private fun initElements() {
        binding.changeSettingsRedirectButton.setOnClickListener {
            addFragment(R.id.fragment_container, ChangeSettingsFragment())
        }
        binding.createCustomLookButton.setOnClickListener {
            addFragment(R.id.fragment_container, HandmadeSelectionFragment())
        }
        binding.createRandomLookButton.setOnClickListener {
            addFragment(R.id.fragment_container, RandomSelectionFragment())
        }
        binding.wardrobeRedirectButton.setOnClickListener {
            addFragment(R.id.fragment_container, MyWardrobeFragment())
        }
    }

    private fun addFragment(container: Int, fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(container, fragment)
            .addToBackStack(null)
            .commit()
    }

}