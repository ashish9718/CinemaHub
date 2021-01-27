package com.ashish.moviestreamingapp.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.ashish.moviestreamingapp.R
import com.ashish.moviestreamingapp.activities.StartActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        auth = Firebase.auth
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        Handler().postDelayed({
            checkUser(user)
        }, 3000)
    }

    private fun checkUser(user: FirebaseUser?) {
        if (user != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else{
            startActivity(Intent(this, StartActivity::class.java))
            finish()
        }
    }
}