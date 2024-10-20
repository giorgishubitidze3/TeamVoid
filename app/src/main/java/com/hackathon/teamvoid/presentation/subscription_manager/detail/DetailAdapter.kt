package com.hackathon.teamvoid.presentation.subscription_manager.detail

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hackathon.teamvoid.R
import com.hackathon.teamvoid.domain.model.Transaction
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

class DetailAdapter(var list: List<Transaction>): RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

    private var totalSum: Double = 0.0

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
       val date = itemView.findViewById<TextView>(R.id.tv_detail_date)
       val amount = itemView.findViewById<TextView>(R.id.tv_detail_amount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_detail,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]

        holder.date.text = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date(currentItem.date))
        holder.amount.text = currentItem.amount

        updateTotalSum(currentItem.amount)
    }

    private fun updateTotalSum(amount: String) {
        val cleanedAmount = amount.replace("-", "").trim()
        when {
            cleanedAmount.contains("$") -> {
                val value = cleanedAmount.replace("$", "").toDoubleOrNull()
                if (value != null) {
                    totalSum += value * 2.72
                    Log.d("DetailAdapter", "Added dollar amount: ${value * 2.72}, Total sum now: $totalSum")
                }
            }
            cleanedAmount.contains("₾") -> {
                val value = cleanedAmount.replace("₾", "").toDoubleOrNull()
                if (value != null) {
                    totalSum += value
                    Log.d("DetailAdapter", "Added GEL amount: $value, Total sum now: $totalSum")
                }
            }
            else -> {
                Log.d("DetailAdapter", "Amount format not recognized: $cleanedAmount")
            }
        }
    }

    fun updateList(newList: List<Transaction>) {
        list = newList
        totalSum = 0.0 // Reset total sum before calculating again
        for (transaction in list) {
            updateTotalSum(transaction.amount) // Update total sum for the new list
        }
        notifyDataSetChanged() // Notify the adapter to refresh the RecyclerView
    }

    fun getTotalSum(): Double {
        return (totalSum * 100).roundToInt() / 100.0 // Round to two decimal places
    }
}