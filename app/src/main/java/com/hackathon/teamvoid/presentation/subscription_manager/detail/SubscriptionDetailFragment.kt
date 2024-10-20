package com.hackathon.teamvoid.presentation.subscription_manager.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hackathon.teamvoid.R
import com.hackathon.teamvoid.presentation.home.TransactionViewModel


class SubscriptionDetailFragment : Fragment() {
    private var transactionId: String? = null
    private var companyName: String? = null
    private var amount: String? = null
    private var date: Long = 0

    private lateinit var adapter: DetailAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            companyName = it.getString("companyName")
            amount = it.getString("amount")
            date = it.getLong("date")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_subscription_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(requireActivity()).get(TransactionViewModel::class.java)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_detail)
        adapter = DetailAdapter(emptyList())
        recyclerView.adapter = adapter


        companyName?.let { company ->
            view.findViewById<TextView>(R.id.detail_name).text = companyName

            when(companyName){
                "Spotify" -> {
                    Glide.with(requireContext()
                        ).load(R.drawable.spotify).into(view.findViewById(R.id.detail_img))}
                "Google" ->{
                    Glide.with(requireContext()
                    ).load(R.drawable.google_logo).into(view.findViewById(R.id.detail_img))}
                "Cavea Plus" ->{
                    Glide.with(requireContext()
                    ).load(R.drawable.cavea_logo).into(view.findViewById(R.id.detail_img))}
                "Glovo Prime" ->{
                    Glide.with(requireContext()
                    ).load(R.drawable.glovo).into(view.findViewById(R.id.detail_img))}
                "Netflix"->{
                    Glide.with(requireContext()
                    ).load(R.drawable.netflix_logo).into(view.findViewById(R.id.detail_img))}
            }

            val filteredTransactions = viewModel.getTransactionsByCompany(company)
            adapter.updateList(filteredTransactions)

            val totalSum = adapter.getTotalSum()
            val formattedSum = String.format("%.2f", totalSum)

            view.findViewById<TextView>(R.id.detail_sum).text = "₾ $formattedSum"
        }



    }

}