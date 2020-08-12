package com.example.pass

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pass.bucket.BucketFragment
import com.example.pass.menu.MenuFragment

class PassesPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa){
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            MenuFragment.newInstance()
        } else {
            BucketFragment.newInstance()

        }
    }
}