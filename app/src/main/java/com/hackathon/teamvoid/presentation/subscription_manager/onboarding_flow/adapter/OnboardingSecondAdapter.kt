package com.hackathon.teamvoid.presentation.subscription_manager.onboarding_flow.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hackathon.teamvoid.R
import com.hackathon.teamvoid.domain.model.Transaction
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class OnboardingSecondAdapter( private val transactions: List<Transaction>): RecyclerView.Adapter<OnboardingSecondAdapter.ViewHolder>() {

    private val checkedStates = BooleanArray(transactions.size)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val upcomingDate = itemView.findViewById<TextView>(R.id.tv_subscription_date)
        val checkBox = itemView.findViewById<CheckBox>(R.id.cb_subscription)
        val img = itemView.findViewById<ImageView>(R.id.img_subscription)
        val name = itemView.findViewById<TextView>(R.id.tv_subscription_name)
        val price = itemView.findViewById<TextView>(R.id.tv_subscription_price)

        init {
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                // Update the checked state
                checkedStates[adapterPosition] = isChecked
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item_subscription_analyzed, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = transactions[position]


        holder.upcomingDate.text = addOneMonthAndFormat(currentItem.date)
        holder.price.text = currentItem.amount
        holder.name.text = currentItem.company
        holder.checkBox.isChecked = checkedStates[position]

        when(currentItem.company){
            "Spotify" -> {
                Glide.with(holder.img.context)
                    .load(R.drawable.spotify).into(holder.img)}
            "Google" ->{
                Glide.with(holder.img.context)
                    .load(R.drawable.google_logo).into(holder.img)}
            "Cavea Plus" ->{
                Glide.with(holder.img.context).load(R.drawable.cavea_logo).into(holder.img)}
            "Glovo Prime" ->{
                Glide.with(holder.img.context
                ).load(R.drawable.glovo).into(holder.img)}
            "Netflix"->{
                Glide.with(holder.img.context
                ).load(R.drawable.netflix_logo).into(holder.img)}

        }

    }

    fun addOneMonthAndFormat(timeInMillis: Long): String {
        val calendar = Calendar.getInstance().apply {
            setTimeInMillis(timeInMillis)
            add(Calendar.MONTH, 1)
        }
        val dateFormat = SimpleDateFormat("dd MMM", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    fun getCheckedTransactions(): List<Transaction> {
        return transactions.filterIndexed { index, _ -> checkedStates[index] }
    }

}

