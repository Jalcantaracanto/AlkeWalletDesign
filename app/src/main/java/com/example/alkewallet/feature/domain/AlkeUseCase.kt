package com.example.alkewallet.feature.domain

import com.example.alkewallet.feature.data.local.AlkeDataSet
import com.example.alkewallet.feature.data.model.User

class AlkeUseCase {

    val alkeDataSet = AlkeDataSet()

    fun getAllUsers(): MutableList<User> {
        return alkeDataSet.createUserDataSet()
    }

    fun login(email: String, password: String): Boolean {
        return alkeDataSet.loginUser(email, password)
    }

}