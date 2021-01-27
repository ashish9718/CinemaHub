package com.ashish.moviestreamingapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.ashish.moviestreamingapp.R
import com.ashish.moviestreamingapp.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_main_home.*

class MainHome : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpTabs()
    }

    private fun setUpTabs() {
        viewpager.adapter = activity?.let { ViewPagerAdapter(it.supportFragmentManager) }
        tab_layout.setupWithViewPager(viewpager)
    }

}