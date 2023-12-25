package com.onion.weatherreportonion.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.onion.weatherreportonion.databinding.ActivityRegistrationBinding
import com.onion.weatherreportonion.ui.MainActivity

class RegistrationActivity: AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initElements()
    }

    private fun initElements() {
        binding.loginRedirect.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        binding.signupButton.setOnClickListener { enter() }
        binding.googleSignUp.setOnClickListener { enter() }
        binding.vkSignUp.setOnClickListener { enter() }
        binding.yandexSignUp.setOnClickListener { enter() }
    }

    private fun enter() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}