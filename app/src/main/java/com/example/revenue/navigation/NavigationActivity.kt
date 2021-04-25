package com.example.revenue.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.revenue.R
import com.example.revenue.revenue.RevenueFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        val fragment = RevenueFragment()
        CurrentFragment(fragment)

        val bottom_navigation = findViewById<BottomNavigationView>(R.id.act_revenue_bottom_navigation)
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
//                R.id.ic_home -> CurrentFragment(revenueFragment)
//                R.id.ic_favorite -> CurrentFragment(favoriteFragment)
            }
            true
        }



    }

    private fun CurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.act_revenue_frame,fragment)
            commit()
        }
    }
}