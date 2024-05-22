package com.example.alkewallet.feature.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alkewallet.feature.data.model.Transaction
import com.example.alkewallet.feature.domain.TransactionUseCase

class TransactionViewModel : ViewModel() {

    private val _transactions = MutableLiveData<MutableList<Transaction>>()
    val transactions: LiveData<MutableList<Transaction>> get() = _transactions

    private val transactionUseCase = TransactionUseCase()

    init {
        _transactions.value = transactionUseCase.getAllTransaction()
    }

    fun addTransaction(transaction: Transaction) {
        transactionUseCase.addTransaction(transaction)
        _transactions.value = transactionUseCase.getAllTransaction()
    }

    fun getLastTransactionId(): Long {
        return transactionUseCase.getLastTransactionId()
    }
}