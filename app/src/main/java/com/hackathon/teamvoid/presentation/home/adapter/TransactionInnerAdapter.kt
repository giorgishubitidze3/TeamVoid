package com.hackathon.teamvoid.presentation.home.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.hackathon.teamvoid.R
import com.hackathon.teamvoid.domain.model.Card
import com.hackathon.teamvoid.domain.model.Transaction
import com.hackathon.teamvoid.presentation.home.TransactionViewModel

class TransactionInnerAdapter(private val list : List<Transaction>,private val viewModel: TransactionViewModel): RecyclerView.Adapter<TransactionInnerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val img = itemView.findViewById<ImageView>(R.id.img_transaction_type)
        val transactionName = itemView.findViewById<TextView>(R.id.tv_transaction_name)
        val transactionType = itemView.findViewById<TextView>(R.id.tv_transaction_type)
        val transactionAmount = itemView.findViewById<TextView>(R.id.tv_transaction_amount)
        init {
            itemView.setOnLongClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val selectedTransaction = list[position]
                    val dialogBuilder = AlertDialog.Builder(itemView.context)
                    dialogBuilder.setMessage("Are you sure you want to add this item to the subscriptions?")
                        .setCancelable(false)
                        .setPositiveButton("Yes") { dialog, _ ->
                            viewModel.addTransactionManually(selectedTransaction)
                            dialog.dismiss()
                        }
                        .setNegativeButton("No") { dialog, _ ->
                            dialog.dismiss()
                        }

                    val alert = dialogBuilder.create()
                    alert.setTitle("Add Subscription")
                    alert.show()
                }

                true
            }
        }

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