package com.example.alkewallet.feature.data.model

data class Wallet (
    val idWallet: Long,
    val balance: Double,
    val transactions: MutableList<Transaction> = mutableListOf()
)