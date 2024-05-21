package com.example.alkewallet.feature.domain

import com.example.alkewallet.feature.data.local.TransactionDataSet
import com.example.alkewallet.feature.data.model.Transaction

class TransactionUseCase {

    val transactionDataSet = TransactionDataSet()

    fun getAllTransaction(): MutableList<Transaction> {
        return transactionDataSet.getAllTransaction()
    }

    fun createNewTransaction(transaction: Transaction) {
        return transactionDataSet.createNewTransaction(transaction)
    }

}