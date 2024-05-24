package com.example.alkewallet.feature.data.model

data class Wallet (
    val idWallet: Long,
    val balance: Double,
){
    companion object {
        val EMPTY = Wallet(
            idWallet = -1,
            balance = 0.0
        )
    }
}