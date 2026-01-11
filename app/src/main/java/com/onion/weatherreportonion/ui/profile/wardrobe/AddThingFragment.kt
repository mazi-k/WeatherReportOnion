package com.onion.weatherreportonion.ui.profile.wardrobe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.onion.weatherreportonion.R
import com.onion.weatherreportonion.databinding.FragmentAddThingBinding
import com.onion.weatherreportonion.databinding.FragmentSettingsBinding

class AddThingFragment: Fragment() {

    private lateinit var binding: FragmentAddThingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_thing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAddThingBinding.bind(view)

        initElements()
    }

    private fun initElements() {
        binding.addButton.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, MyWardrobeFragment())
                .commit()
        }

    }
}