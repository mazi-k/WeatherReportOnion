package com.onion.weatherreportonion.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.onion.weatherreportonion.R
import com.onion.weatherreportonion.databinding.FragmentUserProfileBinding

class UserProfileFragment: Fragment() {

    private lateinit var binding: FragmentUserProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentUserProfileBinding.bind(view)

        initElements()
    }

    private fun initElements() {
        binding.unfollowButton.visibility = View.GONE
        binding.followButton.setOnClickListener {
            binding.unfollowButton.visibility = View.VISIBLE
            binding.followButton.visibility = View.GONE
        }

        binding.unfollowButton.setOnClickListener {
            binding.followButton.visibility = View.VISIBLE
            binding.unfollowButton.visibility = View.GONE
        }
    }
}