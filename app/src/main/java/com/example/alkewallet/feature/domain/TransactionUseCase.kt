package com.example.alkewallet.feature.domain

import com.example.alkewallet.feature.data.local.TransactionDataSet
import com.example.alkewallet.feature.data.model.Transaction

class TransactionUseCase(private val transactionDataSet: TransactionDataSet = TransactionDataSet()) {

    fun getAllTransaction(): MutableList<Transaction> {
        return transactionDataSet.getAllTransaction()
    }

    fun addTransaction(transaction: Transaction) {
        transactionDataSet.addTransaction(transaction)
    }

    fun getLastTransactionId(): Long {
        val transactions = transactionDataSet.getAllTransaction()
        return transactions.maxOfOrNull { it.id } ?: 0
    }
}