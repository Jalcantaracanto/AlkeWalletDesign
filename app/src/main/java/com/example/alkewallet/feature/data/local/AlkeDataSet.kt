package com.example.alkewallet.feature.data.local

import android.util.Log
import com.example.alkewallet.feature.data.model.User
import com.example.alkewallet.feature.data.model.Wallet

class AlkeDataSet {
    val users: MutableList<User> = createUserDataSet()

     private fun createUserDataSet(): MutableList<User> {
        // Mantén este método privado
        return mutableListOf(
            User(100, "Yara", "Khalil",  "123", "12345", Wallet(100, 500.00), "pp2"),
            User(101, "Sara", "Ibrahim",  "sara.ibrahim@example.com", "sara", Wallet(101, 250.00), "pp1"),
            User(102, "Ahmad", "Ibrahim",  "ahmad.ibrahim@example.com", "ahmad", Wallet(102, 710.00), "pp3"),
            User(103, "Reem", "Khaled", "reem.khaled@example.com", "reem", Wallet(103, 423.00), "pp4"),
            User(104, "Hiba", "Saleh",  "hiba.saleh@example.com", "hiba", Wallet(104, 100.00), "pp5"),
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