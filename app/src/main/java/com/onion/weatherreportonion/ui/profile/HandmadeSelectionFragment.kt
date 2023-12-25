package com.onion.weatherreportonion.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.onion.weatherreportonion.R
import com.onion.weatherreportonion.databinding.FragmentHandmadeSelectionBinding
import com.onion.weatherreportonion.ui.profile.wardrobe.MyWardrobeFragment

class HandmadeSelectionFragment: Fragment() {

    private lateinit var binding: FragmentHandmadeSelectionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_handmade_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHandmadeSelectionBinding.bind(view)
        initElements()
    }


    private fun initElements() {
        binding.doneButton.setOnClickListener {
            Toast.makeText(requireContext(), "Набор опубликован", Toast.LENGTH_LONG).show()
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, MyProfileFragment())
                .commit()
        }
    }
}