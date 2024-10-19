package com.hackathon.teamvoid.data.local.test_stuff

import com.hackathon.teamvoid.R
import com.hackathon.teamvoid.domain.model.Card
import com.hackathon.teamvoid.domain.model.Transaction

object TestData {
    val cardList: List<Card> = mutableListOf(
        Card(img = R.drawable.image_test,cardName = "Space ანგარიში", cardType = "SPACE ბარათი", balance = "833.00"),
        Card(img = R.drawable.tbc_student_card,cardName = "ბარათი", cardType = "STUDENT CARD", balance = "20.35"),
        Card(img = R.drawable.tbc_standard_card,cardName = "ბარათი", cardType = "STANDARD CARD", balance = "893.63"),
    )

    val transactionList : List<Transaction> = mutableListOf(
        Transaction("პირადი გადარიცხვა თიბისი ბანკი", "გადარიცხვები",1729340515393,"-120.00 ₾"),
        Transaction("POS wallet - Ori Nabiji, Payment Example", "პროდუქტები სახლისთვის",1729340661677,"-5.00 ₾"),
        Transaction("ანაზღაურება ხელშეკრულების მიხედვით", "სხვა შემოსავლები",1729340518393,"1170.00 ₾"),
        Transaction("POS - EGREVE.BOG.GE, 501.00 GEL", "თანხის განაღდება",1729340518393,"-501.00 ₾"),
        Transaction("პირადი გადარიცხვა თიბისი ბანკი", "გადარიცხვები",1329330515393,"-120.00 ₾"),
        Transaction("POS wallet - Ori Nabiji, Payment Example", "პროდუქტები სახლისთვის",1329340661677,"-5.00 ₾"),
        Transaction("ანაზღაურება ხელშეკრულების მიხედვით", "სხვა შემოსავლები",1329330518393,"1170.00 ₾"),
        Transaction("POS - EGREVE.BOG.GE, 501.00 GEL", "თანხის განაღდება",1329330518393,"-501.00 ₾"),
    )
}