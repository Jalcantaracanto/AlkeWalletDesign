package com.example.alkewallet.feature.data.local

import android.util.Log
import com.example.alkewallet.feature.data.model.Transaction
import com.example.alkewallet.feature.data.model.Transferencia

class TransactionDataSet {

    private val transactions: MutableList<Transaction>

    init {
        transactions = createInitialDataSet()
    }

    private fun createInitialDataSet(): MutableList<Transaction> {
        return mutableListOf(
            Transaction(1, 15.00, "Oct 14, 10:24 AM", 100, 101,imgUser = "pp1"),
            Transaction(2, 21.30, "Oct 16, 11:00 AM", 100, 102,imgUser = "pp3"),
            Transaction(3, 12.40, "Oct 17, 12:00 PM", 101, 100,imgUser = "pp1")
        )
    }

    fun getAllTransaction(): MutableList<Transaction> {
        return transactions.toMutableList() // Devuelve una copia para evitar modificaciones externas
    }

    fun addTransaction(transaction: Transaction) {
        if (transactions.none { it.id == transaction.id }) {
            transactions.add(transaction)
        } else {
            updateTransaction(transaction)
        }
    }

    fun updateTransaction(transaction: Transaction) {
        val index = transactions.indexOfFirst { it.id == transaction.id }
        if (index != -1) {
            transactions[index] = transaction
        }
    }

}