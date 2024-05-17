package com.example.alkewallet.feature.data.model

data class User(
    val userId: Long,
    val userName: String,
    val userLastName: String,
    val userDNI: String,
    val userEmail: String,
    val userPassword: String,
    val wallet: Wallet
)