package com.example.alkewallet.feature.data.local

import com.example.alkewallet.feature.data.model.Transaction
import com.example.alkewallet.feature.data.model.User
import com.example.alkewallet.feature.data.model.Wallet

class AlkeDataSet {
    private val users: MutableList<User>

    init {
        users = createUserDataSet()
    }

    fun createUserDataSet(): MutableList<User> {
        return mutableListOf(
            User(
                100,
                "Javier",
                "Alcantara",
                "18298640-2",
                "javier.alcantara.canto@gmail.com",
                "12345",
                Wallet(
                    100, 100000.00, mutableListOf(
                        Transaction(100, 100.0, 100, 102),
                        Transaction(101, 50.0, 103, 100),
                        Transaction(102, 25.0, 100, 101)
                    )
                )
            ),
            User(
                101,
                "María",
                "Gutiérrez",
                "20485739-5",
                "maria.gutierrez@example.com",
                "abcde",
                Wallet(101, 75000.00)
            ),
            User(
                102,
                "Carlos",
                "Hernández",
                "18652470-3",
                "carlos.hernandez@example.com",
                "qwerty",
                Wallet(102, 50000.00)
            ),
            User(
                103,
                "Ana",
                "Martínez",
                "19983465-1",
                "ana.martinez@example.com",
                "password",
                Wallet(103, 90000.00)
            ),
            User(
                104,
                "Luis",
                "Pérez",
                "17893456-6",
                "luis.perez@example.com",
                "securepass",
                Wallet(104, 120000.00)
            ),
            User(
                105,
                "Sofía",
                "López",
                "19584736-9",
                "sofia.lopez@example.com",
                "12345678",
                Wallet(105, 60000.00)
            )
        )
    }

    fun addNewTransaction(userId: Long, newTransaction: Transaction) {
        val user = getUserById(userId)
        user.wallet.transactions.add(newTransaction)
    }

    private fun getUserById(userId: Long): User {
        return users.find { it.userId == userId }
            ?: throw IllegalArgumentException("User not found")
    }

    fun loginUser(email: String, password: String): Boolean {

        val user = users.find { it.userEmail == email && it.userPassword == password }

        return user != null
    }
}