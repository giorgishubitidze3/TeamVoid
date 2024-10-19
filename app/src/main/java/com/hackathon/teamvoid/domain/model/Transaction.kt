package com.hackathon.teamvoid.domain.model

data class Transaction(
    val name : String,
    val type : String,
    val date : Long,
    val amount: String
)
