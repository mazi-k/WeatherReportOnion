package com.onion.weatherreportonion.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.onion.weatherreportonion.R
import com.onion.weatherreportonion.databinding.FragmentChangeSettingsBinding
import com.onion.weatherreportonion.ui.profile.MyProfileFragment

class ChangeSettingsFragment: Fragment() {

    private lateinit var binding: FragmentChangeSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_change_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentChangeSettingsBinding.bind(view)

        initElements()
    }

    private fun initElements() {
        binding.saveButton.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, SettingsFragment())
                .commit()
        }
    }

}