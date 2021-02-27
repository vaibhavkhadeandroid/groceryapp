package com.vk.dukan.custompagger

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SlidingImagesAdapter(fragment: Fragment, private val itemsCount: Int) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount() = itemsCount

    override fun createFragment(position: Int) = SlidingImageFragment.getInstance(position)
}