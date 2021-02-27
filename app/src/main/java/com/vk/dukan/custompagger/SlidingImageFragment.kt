package com.vk.dukan.custompagger

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.vk.dukan.R
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_sliding.*

class SlidingImageFragment : Fragment(R.layout.fragment_sliding) {

    companion object {
        const val ARG_POSITION = "position"

        fun getInstance(position: Int): Fragment {

          
            val fragment = SlidingImageFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_POSITION, position)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val position = requireArguments().getInt(ARG_POSITION)
        val landingImagesArray = requireContext().resources.getStringArray(R.array.image_urls_array)

//        Glide.with(this)
//            .load(landingImagesArray[position])
//            .placeholder(R.drawable.loading_animation)
//            .error(R.drawable.ic_broken_image)
//            .into(sliding_image)

        /// this is how can add method to Imageview class and  it called as a Extention function
        context?.let { Extensions(it).loadImagefromglide(landingImagesArray[position]) }

    }


}