package com.example.alkewallet.feature.domain

import com.example.alkewallet.feature.data.local.TransactionDataSet
import com.example.alkewallet.feature.data.model.Transaction

class TransactionUseCase {

    private val transactionDataSet = TransactionDataSet()

    fun getAllTransaction(): MutableList<Transaction> {
        return transactionDataSet.getAllTransaction()
    }

    fun addTransaction(transaction: Transaction) {
        transactionDataSet.addTransaction(transaction)
    }

}