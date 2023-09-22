package com.example.secondchance2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceControl.Transaction
import androidx.fragment.app.Fragment
import com.example.secondchance2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(ExploreFragment())


        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_explore -> replaceFragment(ExploreFragment())
                R.id.navigation_swipe -> replaceFragment(SwipeFragment())
                R.id.navigation_post -> replaceFragment(PostFragment())
                R.id.navigation_chat -> replaceFragment(ChatFragment())
                R.id.navigation_you -> replaceFragment(YouFragment())

                else -> {

                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}