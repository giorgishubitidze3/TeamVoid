package com.hackathon.teamvoid.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hackathon.teamvoid.R
import com.hackathon.teamvoid.domain.model.Card
import com.hackathon.teamvoid.domain.model.Transaction

class TransactionInnerAdapter(private val list : List<Transaction>): RecyclerView.Adapter<TransactionInnerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val img = itemView.findViewById<ImageView>(R.id.img_transaction_type)
        val transactionName = itemView.findViewById<TextView>(R.id.tv_transaction_name)
        val transactionType = itemView.findViewById<TextView>(R.id.tv_transaction_type)
        val transactionAmount = itemView.findViewById<TextView>(R.id.tv_transaction_amount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_transaction_inner, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]

        holder.transactionName.text = currentItem.name
        holder.transactionType.text = currentItem.type
        holder.transactionAmount.text = currentItem.amount
    }

}