package com.example.alkewallet.feature.data.model

data class Transaction(
    val id: Long,
    val monto: Double,
    val isSender: Long,
    val idReceiver: Long
)