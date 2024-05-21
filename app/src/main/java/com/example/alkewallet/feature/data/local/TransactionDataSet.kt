package com.example.alkewallet.feature.data.local

import com.example.alkewallet.feature.data.model.Transaction
import com.example.alkewallet.feature.data.model.Transferencia

class TransactionDataSet {

    private val transactions: MutableList<Transaction>

    init {
        transactions = createUserDataSet()
    }

    private fun createUserDataSet(): MutableList<Transaction> {
        return mutableListOf(
            Transaction(1, 500.00, "Oct 14, 10:24 AM", 100, 101),
            Transaction(2, 1500.00, "Oct 16, 11:00 AM", 100, 102),
            Transaction(3, 2500.00, "Oct 17, 12:00 PM", 101, 100),

            )
    }
    fun getAllTransaction() : MutableList<Transaction> {
        return transactions
    }

    fun createNewTransaction(transaction : Transaction) {
        transactions.add(transaction)
    }

}