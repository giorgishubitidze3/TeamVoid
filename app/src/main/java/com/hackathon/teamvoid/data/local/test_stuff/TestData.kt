package com.hackathon.teamvoid.data.local.test_stuff

import com.hackathon.teamvoid.R
import com.hackathon.teamvoid.domain.model.Card

object TestData {
    val cardList: List<Card> = mutableListOf(
        Card(img = R.drawable.image_test,cardName = "Space ანგარიში", cardType = "SPACE ბარათი", balance = "833.00"),
        Card(img = R.drawable.tbc_student_card,cardName = "STUDENT CARD", cardType = "SPACE ბარათი", balance = "20.35"),
        Card(img = R.drawable.tbc_standard_card,cardName = "STANDARD CARD", cardType = "SPACE ბარათი", balance = "893.63"),
    )
}