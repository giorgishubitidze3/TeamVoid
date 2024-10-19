package com.hackathon.teamvoid.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.hackathon.teamvoid.R
import com.hackathon.teamvoid.data.local.test_stuff.TestData
import com.hackathon.teamvoid.presentation.home.adapter.CardAdapter


class HomeFragment : Fragment() {

    private lateinit var toggle : ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        drawerLayout= view.findViewById(R.id.drawer_layout)
        val navView: NavigationView = view.findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(requireActivity(),drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val menuButton: ImageButton = view.findViewById(R.id.imageButton)
        menuButton.setOnClickListener {
            openDrawer()
        }

        //navView.setNavigationItemSelectedListener for navigation

        val cardRecyclerView = view.findViewById<RecyclerView>(R.id.rv_cards)

        val cardAdapter = CardAdapter(TestData.cardList)

        cardRecyclerView.adapter=cardAdapter
    }

    private fun openDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }
}