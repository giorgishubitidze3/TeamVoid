package com.hackathon.teamvoid.domain.model

data class TransactionGroup(
    val date: String,
    val transactions: List<Transaction>
)
