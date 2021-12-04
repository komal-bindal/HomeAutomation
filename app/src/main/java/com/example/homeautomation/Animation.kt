package com.example.homeautomation

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.ramotion.paperonboarding.PaperOnboardingFragment
import com.ramotion.paperonboarding.PaperOnboardingPage
import java.util.*


class Animation : AppCompatActivity() {
    private var fragmentManager: FragmentManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)
        fragmentManager = supportFragmentManager
        val paperOnboardingFragment = PaperOnboardingFragment.newInstance(
            dataforOnboarding
        )
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.add(R.id.frame_layout, paperOnboardingFragment)
        fragmentTransaction.commit()
    }

    private val dataforOnboarding: ArrayList<PaperOnboardingPage>
        private get() {
            val source = PaperOnboardingPage(
                "Make Living Easy",
                "Complete Home Solutions ",
                R.drawable.gradientthree,
                R.drawable.a,
                0
            )
            val source1 = PaperOnboardingPage(
                "Make Living Easy",
                "Making IT happen",
                R.drawable.gradienttwo,
                R.drawable.c,
                0

            )
            val source2 = PaperOnboardingPage(
                "Make Living Easy",
                "Make home a better place",
                R.drawable.gradientone,
                R.drawable.b,
                0
            )
            val elements = ArrayList<PaperOnboardingPage>()
            elements.add(source)
            elements.add(source1)
            elements.add(source2)
            return elements
        }
}
