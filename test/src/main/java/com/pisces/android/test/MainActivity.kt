package com.pisces.android.test

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.pisces.android.test.template.TitleFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val tabs by lazy { arrayListOf("Homer","Other") }

    val fragments by lazy { arrayListOf(HomeFragment(),TitleFragment.newInstance("Other")) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        page.adapter = TabAdapter(supportFragmentManager,fragments,tabs)
        tab.setupWithViewPager(page)
    }
}
