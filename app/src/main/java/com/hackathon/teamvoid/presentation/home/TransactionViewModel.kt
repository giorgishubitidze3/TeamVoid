package com.hackathon.teamvoid.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackathon.teamvoid.data.local.test_stuff.TestData
import com.hackathon.teamvoid.domain.model.Transaction
import com.hackathon.teamvoid.domain.model.TransactionGroup
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class TransactionViewModel:ViewModel() {
    val transactions = MutableLiveData<List<Transaction>>(TestData.transactionList)

    val subscriptions = MutableLiveData<List<Transaction>>()

    val onboardingState = MutableLiveData<Boolean>(false)

    private val _groupedTransactions = MutableLiveData<List<TransactionGroup>>()
    val groupedTransactions: LiveData<List<TransactionGroup>> get() = _groupedTransactions

    private val _recurringTransactions = MutableLiveData<List<Transaction>>()
    val recurringTransactions: LiveData<List<Transaction>> get() = _recurringTransactions

    init {
        transactions.observeForever { transactionList ->
            _groupedTransactions.value = groupTransactionsByDate(transactionList)
                .map { TransactionGroup(it.key, it.value) }
        }
    }

    fun updateRecurringTransactions(transactions: List<Transaction>) {
            _recurringTransactions.value = findRecurringTransactions(transactions)
    }
    fun addTransactionManually(transaction: Transaction) {
        val updatedList = subscriptions.value?.toMutableList() ?: mutableListOf()
        if (!updatedList.contains(transaction)) {
            updatedList.add(transaction)
            subscriptions.value = updatedList
        }
    }

    fun getTransactionsByCompany(companyName: String): List<Transaction> {
        return transactions.value?.filter { it.company == companyName } ?: emptyList()
    }

    private fun findRecurringTransactions(transactions: List<Transaction>): List<Transaction> {
        val recurringList = mutableListOf<Transaction>()
        val groupedByName = transactions.groupBy { it.name }

        val currentDate = System.currentTimeMillis()

        groupedByName.forEach { (_, transactionList) ->
            if (transactionList.size > 1) {
                val closestTransaction = transactionList.minByOrNull {
                    Math.abs(it.date - currentDate)
                }

                closestTransaction?.let { transaction ->
                    if (transaction.amount < 0.toString()) {
                        recurringList.add(transaction)
                    }
                }
            }
        }
        return recurringList
    }

    private fun getDayOfMonth(date: Long): Int {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = date
        return calendar.get(Calendar.DAY_OF_MONTH)
    }

    private fun groupTransactionsByDate(transactions: List<Transaction>): Map<String, List<Transaction>> {
        return transactions.groupBy {
            SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date(it.date))
        }
    }
}