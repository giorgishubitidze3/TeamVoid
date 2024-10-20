package com.hackathon.teamvoid.presentation.subscription_manager.onboarding_flow

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.hackathon.teamvoid.R
import com.hackathon.teamvoid.presentation.home.TransactionViewModel
import com.hackathon.teamvoid.presentation.subscription_manager.onboarding_flow.adapter.OnboardingSecondAdapter


class   OnboardingSecondFragment : Fragment() {

    private lateinit var adapter: OnboardingSecondAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_onboarding_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(requireActivity())[TransactionViewModel::class.java]
        val navController = Navigation.findNavController(view)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_onboarding_second)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        // Initialize the adapter with an empty list
        adapter = OnboardingSecondAdapter(emptyList())
        recyclerView.adapter = adapter

        viewModel.transactions.value?.let { viewModel.updateRecurringTransactions(it) }

        progressBar.visibility = View.VISIBLE

        viewModel.recurringTransactions.observe(viewLifecycleOwner) { list ->
            progressBar.visibility = View.GONE

            if (list.isNotEmpty()) {
                // Update the adapter's transactions and notify data change
                adapter = OnboardingSecondAdapter(list) // Update to use the same reference
                recyclerView.adapter = adapter
            }
        }

        view.findViewById<ImageButton>(R.id.btn_back_onboarding_secon).setOnClickListener {
            navController.popBackStack()
        }

        view.findViewById<Button>(R.id.btn_continue_second).setOnClickListener {
            val checkedTransactions = adapter.getCheckedTransactions()
            if (checkedTransactions.isEmpty()) {
                showDialog("Please select at least one subscription.")
            } else {
                checkedTransactions.forEach { viewModel.addTransactionManually(it) }
                viewModel.onboardingState.value = true
                // You can show a Toast here or navigate to another fragment if needed
                navController.navigate(R.id.action_onboardingSecondFragment_to_subscriptionsHubFragment)
            }
        }


    }

    private fun showDialog(message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle("Alert")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }
}

