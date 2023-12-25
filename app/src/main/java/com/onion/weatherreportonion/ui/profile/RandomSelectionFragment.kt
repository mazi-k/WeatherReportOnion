package com.onion.weatherreportonion.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.onion.weatherreportonion.R
import com.onion.weatherreportonion.databinding.FragmentRandomSelectionBinding

class RandomSelectionFragment: Fragment() {

    private lateinit var binding: FragmentRandomSelectionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_random_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRandomSelectionBinding.bind(view)

        initElements()
    }

    private fun initElements() {
        binding.tryAgainButton.setOnClickListener {  }
        binding.gotItButton.setOnClickListener {
            Toast.makeText(requireContext(), "Набор опубликован", Toast.LENGTH_LONG).show()
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, MyProfileFragment())
                .commit()
        }
    }

    private fun addFragment(container: Int, fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(container, fragment)
            .addToBackStack(null)
            .commit()
    }
}