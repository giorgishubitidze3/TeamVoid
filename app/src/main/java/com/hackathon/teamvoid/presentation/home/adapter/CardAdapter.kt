package com.hackathon.teamvoid.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hackathon.teamvoid.R
import com.hackathon.teamvoid.domain.model.Card

class CardAdapter(val list: List<Card>): RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val img = itemView.findViewById<ImageView>(R.id.img_card)
        val cardName = itemView.findViewById<TextView>(R.id.tv_card_name)
        val cardType = itemView.findViewById<TextView>(R.id.tv_card_type)
        val balance = itemView.findViewById<TextView>(R.id.tv_card_balance)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_cards,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]

        holder.cardName.text = currentItem.cardName
        holder.cardType.text = currentItem.cardType
        holder.balance.text = currentItem.balance

        Glide.with(holder.img.context)
            .load(currentItem.img)
            .into(holder.img)

    }
}