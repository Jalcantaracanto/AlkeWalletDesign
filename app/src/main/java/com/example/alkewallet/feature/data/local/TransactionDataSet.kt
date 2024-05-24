package com.example.alkewallet.feature.data.local

import com.example.alkewallet.feature.data.model.Transaction

class TransactionDataSet {

    private val transactions: MutableList<Transaction>

    /**
     * Inicializa la lista de transacciones con datos iniciales
     */
    init {
        transactions = createInitialDataSet()
    }

    private fun createInitialDataSet(): MutableList<Transaction> {
        return mutableListOf(
            Transaction(1, 15.00, "Oct 14, 10:24 AM", 100, 101, imgUser = "pp1"),
            Transaction(2, 21.30, "Oct 16, 11:00 AM", 100, 102, imgUser = "pp3"),
            Transaction(3, 12.40, "Oct 17, 12:00 PM", 101, 100, imgUser = "pp1")
        )
    }

    /**
     * Obtiene todas las transacciones
     */
    fun getAllTransaction(): MutableList<Transaction> {
        return transactions.toMutableList() // Devuelve una copia para evitar modificaciones externas
    }

    /**
     * Agrega una nueva transacción
     */
    fun addTransaction(transaction: Transaction) {
        if (transactions.none { it.id == transaction.id }) {
            transactions.add(transaction)
        }
    }


}