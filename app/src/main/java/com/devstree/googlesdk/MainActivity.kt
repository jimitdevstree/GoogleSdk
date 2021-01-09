package com.devstree.googlesdk

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.devstree.googlelogin.GoogleLogin
import com.devstree.googlelogin.GoogleLoginResponse
import com.devstree.googlelogin.GoogleLoginResponseData
import com.devstree.googlesdk.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), GoogleLoginResponse {

    private lateinit var binding: ActivityMainBinding
    private var googleSignIn = GoogleLogin()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.btnGoogleSignIn.setOnClickListener {
            googleSignIn.logIn(this, this)
        }

        binding.btnLogOut.setOnClickListener {
            googleSignIn.logout()
            binding.btnLogOut.visibility = GONE
        }
    }

    private fun getUserData(responseData: GoogleLoginResponseData) {
        binding.loginData.text = "User Name: ${responseData.name}\n Email: ${responseData.email}\n"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && data != null) {
            googleSignIn.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onSuccess(responseData: GoogleLoginResponseData) {
        getUserData(responseData)
        binding.loginData.visibility = VISIBLE
        binding.btnLogOut.visibility = VISIBLE
    }

    override fun onFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}