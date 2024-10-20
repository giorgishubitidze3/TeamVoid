package com.hackathon.teamvoid.data.local.test_stuff

import com.hackathon.teamvoid.R
import com.hackathon.teamvoid.domain.model.Card
import com.hackathon.teamvoid.domain.model.Transaction

object TestData {
    val cardList: MutableList<Card> = mutableListOf(
        Card(img = R.drawable.file,cardName = "Space ანგარიში", cardType = "SPACE ბარათი", balance = "833.00"),
        Card(img = R.drawable.tbc_student_card,cardName = "ბარათი", cardType = "STUDENT CARD", balance = "20.35"),
        Card(img = R.drawable.tbc_standard_card,cardName = "ბარათი", cardType = "STANDARD CARD", balance = "893.63")
    )

    val transactionList : List<Transaction> = mutableListOf(
        Transaction("SPOTIFY, შვედეთი", "საყიდლები-სხვა",1729368000000,"-4.99 $", "Spotify"),
        Transaction("GOOGLE *Play Pass, აშშ", "საყიდლები-სხვა",1729340661677,"-5.29 $", "Google"),
        Transaction("Cavea Plus, GEL9.00", "საყიდლები-სხვა",1729340661677,"-9.00 ₾", "Cavea Plus"),
        Transaction("Glovo Prime, Georgia", "საყიდლები-სხვა",1729022400000,"-8.99 ₾", "Glovo Prime"),
        Transaction("ანაზღაურება ხელშეკრულების მიხედვით", "სხვა შემოსავლები",1729340518393,"1170.00 ₾", "სხვა შემოსავლები"),
        Transaction("Netflix, აშშ", "საყიდლები-სხვა",1728936000000,"-11.99 $", "Netflix"),
        Transaction("SPOTIFY, შვედეთი", "საყიდლები-სხვა",1726776000000,"-4.99 $","Spotify"),
        Transaction("GOOGLE *Play Pass, აშშ", "საყიდლები-სხვა",1726689600000,"-5.29 $","Google"),
        Transaction("Cavea Plus, GEL9.00", "საყიდლები-სხვა",1726689600000,"-9.00 ₾","Cavea Plus"),
        Transaction("POS wallet - Ori Nabiji, Payment Example", "პროდუქტები სახლისთვის",1726430400000,"-5.00 ₾", "Ori Nabiji"),
        Transaction("Glovo Prime, Georgia", "საყიდლები-სხვა",1726430400000,"-8.99 ₾", "Glovo Prime"),
        Transaction("პირადი გადარიცხვა თიბისი ბანკი", "გადარიცხვები",1726344000000,"-120.00 ₾","პირადი გადარიცხვა"),
        Transaction("ანაზღაურება ხელშეკრულების მიხედვით", "სხვა შემოსავლები",1726257600000,"1170.00 ₾" ,"სხვა შემოსავლები")
    )
}