package com.example.alkewallet.feature.domain

import com.example.alkewallet.feature.data.local.TransactionDataSet
import com.example.alkewallet.feature.data.model.Transaction

class TransactionUseCase(private val transactionDataSet: TransactionDataSet = TransactionDataSet()) {

    /**
     * Llama la funcion getAllTransaction de la clase TransactionDataSet
     */
    fun getAllTransaction(): MutableList<Transaction> {
        return transactionDataSet.getAllTransaction()
    }

    /**
     * Llama la funcion addTransaction de la clase TransactionDataSet para agregar una transaccion
     */
    fun addTransaction(transaction: Transaction) {
        transactionDataSet.addTransaction(transaction)
    }

    /**
     * Funcion que realiza la busqueda del ultimo ID de transaccion
     */
    fun getLastTransactionId(): Long {
        val transactions = transactionDataSet.getAllTransaction()
        return transactions.maxOfOrNull { it.id } ?: 0
    }
}