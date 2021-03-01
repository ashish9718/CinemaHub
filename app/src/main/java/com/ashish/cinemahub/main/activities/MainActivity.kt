package com.ashish.cinemahub.main.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ashish.cinemahub.R
import com.ashish.cinemahub.main.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Cinema Hub"
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.elevation = 0F
        setUpTabs()

    }

    private fun setUpTabs() {
        viewpager.adapter = ViewPagerAdapter(supportFragmentManager)
        tab_layout.setupWithViewPager(viewpager)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search -> {
                startActivity(Intent(this, SearchActivity::class.java))
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}


