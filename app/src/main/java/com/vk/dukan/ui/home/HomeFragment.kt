package com.vk.dukan.ui.home

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.vk.dukan.R
import com.vk.dukan.custompagger.Extensions
import com.vk.dukan.custompagger.SlidingImagesAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var imagesArray: Array<String>
    private var currentPage = 0
    private lateinit var slidingImageDots: Array<ImageView?>
    private var slidingDotsCount = 0

    private val slidingCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            for (i in 0 until slidingDotsCount) {
                slidingImageDots[i]?.setImageDrawable(
                    context?.let {
                        ContextCompat.getDrawable(
                            it,
                            R.drawable.non_active_dot
                        )
                    }
                )
            }

            slidingImageDots[position]?.setImageDrawable(
                context?.let {
                    ContextCompat.getDrawable(
                        it,
                        R.drawable.active_dot
                    )
                }
            )
        }
    }

  lateinit  var slidingViewPagerhhhhh: ViewPager2
  lateinit var slider_dots:LinearLayout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        slidingViewPagerhhhhh = root.findViewById(R.id.slidingViewPagerhhhhh)

        slider_dots= root.findViewById(R.id.slider_dots)
        setUpSlidingViewPager()


        return root
    }


    private fun setUpSlidingViewPager() {
        imagesArray = resources.getStringArray(R.array.image_urls_array)

        val landingImagesAdapter = SlidingImagesAdapter(this , imagesArray.size)

        slidingViewPagerhhhhh.adapter=landingImagesAdapter
        slidingViewPagerhhhhh.    registerOnPageChangeCallback(slidingCallback)
        slidingDotsCount = imagesArray.size

        slidingImageDots = arrayOfNulls(slidingDotsCount)

        for (i in 0 until slidingDotsCount) {
            slidingImageDots[i] = ImageView(context)
            slidingImageDots[i]?.setImageDrawable(
                context?.let {
                    ContextCompat.getDrawable(
                        it,
                        R.drawable.non_active_dot
                    )
                }
            )
            val params =
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

            params.setMargins(8, 0, 8, 0)
            slider_dots.addView(slidingImageDots[i], params)
        }

        slidingImageDots[0]?.setImageDrawable(
            context?.let {
                ContextCompat.getDrawable(
                    it,
                    R.drawable.active_dot
                )
            }
        )

        val handler = Handler()
        val update = Runnable {
            if (currentPage == imagesArray.size) {
                currentPage = 0
            }

            //The second parameter ensures smooth scrolling
            slidingViewPagerhhhhh.setCurrentItem(currentPage++, true)
        }

        Timer().schedule(object : TimerTask() {
            // task to be scheduled
            override fun run() {
                handler.post(update)
            }
        }, 3500, 3500)
    }

}