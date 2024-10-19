package com.hackathon.teamvoid.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hackathon.teamvoid.data.local.test_stuff.TestData
import com.hackathon.teamvoid.domain.model.Transaction
import com.hackathon.teamvoid.domain.model.TransactionGroup
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TransactionViewModel:ViewModel() {
    val transactions = MutableLiveData<List<Transaction>>(TestData.transactionList)

    private val _groupedTransactions = MutableLiveData<List<TransactionGroup>>()
    val groupedTransactions: LiveData<List<TransactionGroup>> get() = _groupedTransactions

    init {
        transactions.observeForever { transactionList ->
            _groupedTransactions.value = groupTransactionsByDate(transactionList).map { TransactionGroup(it.key, it.value) }
        }
    }

    private fun groupTransactionsByDate(transactions: List<Transaction>): Map<String, List<Transaction>> {
        return transactions.groupBy {
            SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date(it.date))
        }
    }
}