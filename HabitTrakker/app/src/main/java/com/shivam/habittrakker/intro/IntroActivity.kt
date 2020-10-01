package com.shivam.habittrakker.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.shivam.habittrakker.MainActivity
import com.shivam.habittrakker.R
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : AppCompatActivity() {
lateinit var sip:List<IntroView>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        addView()
        viewPager2.adapter = IntroAdapter(sip)
        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        circleIndicator.setViewPager(viewPager2)
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (position == 2) {
                    animationButton()
                }
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
        })
    }

    private fun addView() {
        sip = listOf(
            IntroView("Welcome to Habit Tracker!", R.drawable.hot_cup),
            IntroView(
                "This app is designed to keep track of your habits, " +
                        "whether it's a good one, or a bad one.", R.drawable.fast_food_s
            ),
            IntroView(
                "Good luck! Tap on the button below to get started with using the app!",
                R.drawable.no_smoking_k
            )
        )
    }
   fun animationButton(){
       btn_start_app.visibility=View.VISIBLE
       btn_start_app.animate().apply{
           duration=1300
       alpha(1f)
       btn_start_app.setOnClickListener {
           val i=Intent(this@IntroActivity,MainActivity::class.java)
           startActivity(i)
       finish()
       }
       }.start()
   }

}
