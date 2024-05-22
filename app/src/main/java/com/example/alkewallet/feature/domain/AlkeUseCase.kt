package com.example.alkewallet.feature.domain

import android.util.Log
import com.example.alkewallet.feature.data.local.AlkeDataSet
import com.example.alkewallet.feature.data.model.User

class AlkeUseCase(private val alkeDataSet: AlkeDataSet = AlkeDataSet()) {

    fun getAllUsers(): MutableList<User> {
        return alkeDataSet.getAllUsers().toMutableList()
    }

    fun getUser(id: Long): User? {
        return alkeDataSet.getUser(id)
    }

    fun authUser(email: String, password: String): User? {
        return alkeDataSet.loginUser(email, password)
    }

    fun updateBalanceUser(user: User, monto: Double, isSend: Boolean): Boolean {
        val nuevoSaldo = if (isSend) {
            user.wallet.balance - monto
        } else {
            user.wallet.balance + monto
        }

        return if (nuevoSaldo >= 0) {
            val nuevaWallet = user.wallet.copy(balance = nuevoSaldo)
            val usuarioActualizado = user.copy(wallet = nuevaWallet)
            alkeDataSet.updateUser(user.userId, usuarioActualizado)
            true
        } else {
            false
        }
    }

    fun getLastUserId(): Long {
        return alkeDataSet.getAllUsers().maxOfOrNull { it.userId } ?: 0
    }

    fun getLastWalletId(): Long {
        return alkeDataSet.getAllUsers().maxOfOrNull { it.wallet.idWallet } ?: 0
    }

    fun addUser(user: User) {
        alkeDataSet.users.add(user)
    }
}