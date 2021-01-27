package com.ashish.moviestreamingapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ashish.moviestreamingapp.R
import com.ashish.moviestreamingapp.fragments.Home
import com.ashish.moviestreamingapp.fragments.MainHome
import com.ashish.moviestreamingapp.fragments.Profile
import com.ashish.moviestreamingapp.fragments.Search
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var user: FirebaseUser
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth
        user = auth.currentUser!!

        makeCurrentFragment(MainHome())
        bottomnavigationbar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> makeCurrentFragment(MainHome())
                R.id.search -> makeCurrentFragment(Search())
                R.id.profile -> makeCurrentFragment(Profile())
            }
            true
        }

    }


    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout, fragment)
            commit()
        }

    private fun signOut() {
//        val loggingType = intent.getStringExtra("loggingType")

//        signout.setOnClickListener {
//            auth.signOut()
//            when (loggingType) {
//                "fb" -> {
//                    LoginManager.getInstance().logOut()   ///for facebook logout
//                }
//                "google" -> {
//                    googleSignInClient.signOut()
//                }
//            }
//            startActivity(Intent(this, StartActivity::class.java))
//            finish()
//        }

    }


}