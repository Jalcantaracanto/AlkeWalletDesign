package com.example.alkewallet.feature.data.local

import android.util.Log
import com.example.alkewallet.feature.data.model.User
import com.example.alkewallet.feature.data.model.Wallet

class AlkeDataSet {
    val users: MutableList<User> = createUserDataSet()

     private fun createUserDataSet(): MutableList<User> {
        // Mantén este método privado
        return mutableListOf(
            User(100, "Javier", "Alcantara", "18298640-2", "123", "12345", Wallet(100, 100000.00)),
            User(101, "María", "Gutiérrez", "20485739-5", "maria.gutierrez@example.com", "abcde", Wallet(101, 75000.00)),
            User(102, "Carlos", "Hernández", "18652470-3", "carlos.hernandez@example.com", "qwerty", Wallet(102, 50000.00)),
            User(103, "Ana", "Martínez", "19983465-1", "ana.martinez@example.com", "password", Wallet(103, 90000.00)),
            User(104, "Luis", "Pérez", "17893456-6", "luis.perez@example.com", "securepass", Wallet(104, 120000.00)),
            User(105, "Sofía", "López", "19584736-9", "sofia.lopez@example.com", "12345678", Wallet(105, 60000.00))
        )
    }

    fun getAllUsers(): List<User> {
        return users

    }

    fun loginUser(email: String, password: String): User? {
        return users.find { it.userEmail == email && it.userPassword == password }
    }

    fun getUser(userId: Long): User {
        return users.find { it.userId == userId } ?: throw IllegalArgumentException("User not found")
    }

    fun updateUser(userId: Long, updatedUser: User): User {
        val index = users.indexOfFirst { it.userId == userId }
        if (index != -1) {
            users[index] = updatedUser
        }
        return updatedUser
    }

    override fun toString(): String {
        return users.joinToString(separator = "\n") { user ->
            "User(id=${user.userId}, email=${user.userEmail}, balance=${user.wallet.balance})"
        }
    }
}