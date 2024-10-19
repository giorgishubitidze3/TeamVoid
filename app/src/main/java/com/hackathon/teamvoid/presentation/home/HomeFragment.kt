package com.hackathon.teamvoid.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.hackathon.teamvoid.R
import com.hackathon.teamvoid.data.local.test_stuff.TestData
import com.hackathon.teamvoid.presentation.home.adapter.CardAdapter
import com.hackathon.teamvoid.presentation.home.adapter.TransactionOuterAdapter


class HomeFragment : Fragment() {

    private lateinit var toggle : ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var transactionViewModel: TransactionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(view)

        transactionViewModel = ViewModelProvider(requireActivity())[TransactionViewModel::class.java]


        val outerRecyclerView = view.findViewById<RecyclerView>(R.id.rv_transactions_outer)
        outerRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        transactionViewModel.groupedTransactions.observe(requireActivity(), { transactionGroups ->
            outerRecyclerView.adapter = TransactionOuterAdapter(transactionGroups)
        })


        drawerLayout= view.findViewById(R.id.drawer_layout)
        val navView: NavigationView = view.findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(requireActivity(),drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val menuButton: ImageButton = view.findViewById(R.id.btn_back_sub_manager)
        menuButton.setOnClickListener {
            openDrawer()
        }

        //navView.setNavigationItemSelectedListener for navigation

        val cardRecyclerView = view.findViewById<RecyclerView>(R.id.rv_cards)

        val cardAdapter = CardAdapter(TestData.cardList)

        cardRecyclerView.adapter=cardAdapter


        view.findViewById<CardView>(R.id.card_sub_manager).setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_subscriptionManagerFragment)
        }
    }

    private fun openDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }
}