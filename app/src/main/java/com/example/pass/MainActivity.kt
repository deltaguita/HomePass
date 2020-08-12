package com.example.pass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val pagerAdapter = PassesPagerAdapter(this)
        view_pager.adapter = pagerAdapter
        TabLayoutMediator(tab_layout,view_pager,object:TabLayoutMediator.TabConfigurationStrategy{
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                tab.text = when (position) {
                    0 -> "Menu"
                    1 -> "Bucket"
                    else -> null
                }
            }

        }).attach()
    }
}