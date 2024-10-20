package com.hackathon.teamvoid.presentation.subscription_manager

import SubscriptionManagerAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.hackathon.teamvoid.R
import com.hackathon.teamvoid.domain.model.Transaction
import com.hackathon.teamvoid.presentation.home.TransactionViewModel


class SubscriptionsHubFragment : Fragment() {

    private lateinit var adapter: SubscriptionManagerAdapter
    private lateinit var viewModel: TransactionViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_subscriptions_hub, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        viewModel = ViewModelProvider(requireActivity())[TransactionViewModel::class.java]

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_subscription_manager)
        adapter = SubscriptionManagerAdapter(emptyList()){ transaction ->
            navigateToDetail(transaction, navController)
        }
        recyclerView.adapter = adapter

        viewModel.subscriptions.observe(viewLifecycleOwner) { list ->
            adapter.updateList(list)
            updateTotalSum()
        }

        view.findViewById<ImageButton>(R.id.btn_back_onboarding_second_hub).setOnClickListener {
            navController.navigate(R.id.action_subscriptionsHubFragment_to_homeFragment)
        }
    }

    private fun updateTotalSum() {
        view?.post {
            val totalSum = adapter.getTotalSum()
            val formattedSum = String.format("%.2f", totalSum)
            view?.findViewById<TextView>(R.id.total_sum_subscriptions)?.text = "₾ $formattedSum"
        }
    }

    private fun navigateToDetail(transaction: Transaction, navController: NavController) {
        val args = Bundle().apply {
            putString("companyName", transaction.company)
            putString("amount", transaction.amount)
            putLong("date", transaction.date)
        }
        navController.navigate(R.id.action_subscriptionsHubFragment_to_subscriptionDetailFragment, args)
    }
}