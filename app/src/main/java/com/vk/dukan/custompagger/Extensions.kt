package com.vk.dukan.custompagger

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.vk.dukan.R

class Extensions(var context: Context) {


    fun ImageView.loadImage(url: String) {
        Glide.with(this)
            .load(url)
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image)
            .into(this)
    }


    fun loadImagefromglide(url: String){

        ImageView(context).loadImage(url)
    }
}