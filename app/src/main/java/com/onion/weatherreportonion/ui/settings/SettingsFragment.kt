package com.onion.weatherreportonion.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.onion.weatherreportonion.R
import com.onion.weatherreportonion.databinding.FragmentSettingsBinding
import com.onion.weatherreportonion.ui.login.LoginActivity

class SettingsFragment: Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSettingsBinding.bind(view)

        initElements()
    }

    private fun initElements() {
        binding.aboutButton.setOnClickListener {  }
        binding.changeSettingsRedirectButton.setOnClickListener {
            addFragment(R.id.fragment_container, ChangeSettingsFragment())
        }
        binding.exitButton.setOnClickListener {
            startActivity(Intent(requireContext(), LoginActivity::class.java))
        }
        binding.importVK.setOnClickListener {  }
        binding.importContacts.setOnClickListener {  }

    }

    private fun addFragment(container: Int, fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(container, fragment)
            .addToBackStack(null)
            .commit()
    }

}