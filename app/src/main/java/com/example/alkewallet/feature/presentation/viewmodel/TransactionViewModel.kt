package com.example.alkewallet.feature.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alkewallet.feature.data.model.Transaction
import com.example.alkewallet.feature.domain.TransactionUseCase

class TransactionViewModel: ViewModel() {

    private val _transactions = MutableLiveData<MutableList<Transaction>>()
    val transaction: LiveData<MutableList<Transaction>> get() = _transactions

    private val transaccionUseCase = TransactionUseCase()

    init {
        _transactions.value = transaccionUseCase.getAllTransaction()
    }

    fun addTransaccion(transaction: Transaction) {
        val currentList = _transactions.value ?: mutableListOf()
        currentList.add(transaction)
        _transactions.value = currentList
        transaccionUseCase.addTransaction(transaction)
    }

    fun getLastTransactionId(): Long {
        val currentList = _transactions.value ?: mutableListOf()

        val lastTransactionId = if (currentList.isNotEmpty()) {
            currentList.last().id
        } else {
            0
        }
        return lastTransactionId
    }
}