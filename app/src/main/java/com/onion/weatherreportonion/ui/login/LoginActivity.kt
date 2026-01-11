package com.onion.weatherreportonion.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.onion.weatherreportonion.databinding.ActivityLoginBinding
import com.onion.weatherreportonion.ui.MainActivity

class LoginActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

       initElements()
    }

    private fun initElements() {
        binding.signupRedirect.setOnClickListener {
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