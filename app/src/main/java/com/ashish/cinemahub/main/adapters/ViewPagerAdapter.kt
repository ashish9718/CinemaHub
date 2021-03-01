package com.ashish.cinemahub.main.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ashish.cinemahub.main.fragments.Home
import com.ashish.cinemahub.main.fragments.Movies
import com.ashish.cinemahub.main.fragments.TVShows

class ViewPagerAdapter(supportFragmentManager: FragmentManager) :
    FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
         return when (position) {
            0 -> {
                Home()
            }
            1 -> {
                TVShows()
            }
            2 -> {
                Movies()
            }
            else -> {
                Home()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> {
                return "Home"
            }
            1 -> {
                return "TVShows"
            }
            2 -> {
                return "Movies"
            }
        }
        return super.getPageTitle(position)
    }

}