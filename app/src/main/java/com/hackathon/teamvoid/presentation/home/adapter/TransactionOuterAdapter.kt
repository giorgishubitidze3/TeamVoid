package com.hackathon.teamvoid.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hackathon.teamvoid.R
import com.hackathon.teamvoid.domain.model.Card
import com.hackathon.teamvoid.domain.model.Transaction
import com.hackathon.teamvoid.domain.model.TransactionGroup
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TransactionOuterAdapter(private val groups: List<TransactionGroup>): RecyclerView.Adapter<TransactionOuterAdapter.ViewHolder>() {




    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val transactionDate = itemView.findViewById<TextView>(R.id.tv_transaction_date)
        val childRv = itemView.findViewById<RecyclerView>(R.id.rv_transaction_inner)

        fun bind(group: TransactionGroup) {
            transactionDate.text = group.date

            // Set up the inner RecyclerView
            childRv.adapter = TransactionInnerAdapter(group.transactions)
            childRv.layoutManager = LinearLayoutManager(itemView.context)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_transaction_parent,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return groups.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(groups[position])
    }


}