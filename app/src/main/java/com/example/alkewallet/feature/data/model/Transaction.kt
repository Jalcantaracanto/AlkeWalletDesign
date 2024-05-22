package com.example.alkewallet.feature.data.model


data class Transaction(
    val id: Long,
    val balance: Double,
    val date: String,
    val idSender: Long,
    val idReceiver: Long,
    val imgUser: String = "",
)