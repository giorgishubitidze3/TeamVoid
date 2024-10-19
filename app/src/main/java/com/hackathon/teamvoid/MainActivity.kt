package com.hackathon.teamvoid

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val navController by lazy{
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window,false)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val bottomNavigationBar = findViewById<BottomNavigationView>(R.id.bottom_nav_bar)

        bottomNavigationBar.setupWithNavController(navController)

        navController.addOnDestinationChangedListener{_, destination, _ ->
            if(destination.id in listOf(
                R.id.homeFragment,
                R.id.depositFragment,
                R.id.transferFragment,
                R.id.utilitiesFragment
            )){
                bottomNavigationBar.visibility = View.VISIBLE
            }else{
                bottomNavigationBar.visibility = View.GONE
            }
        }



    }
}