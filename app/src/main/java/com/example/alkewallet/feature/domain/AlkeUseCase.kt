package com.example.alkewallet.feature.domain

import android.util.Log
import com.example.alkewallet.feature.data.local.AlkeDataSet
import com.example.alkewallet.feature.data.model.User

class AlkeUseCase {

    private val alkeDataSet = AlkeDataSet()

    fun getAllUsers(): MutableList<User> {
        return alkeDataSet.users
    }

    fun login(email: String, password: String): User? {
        return alkeDataSet.loginUser(email, password)
    }

    fun getUser(idUser: Long): User {
        return alkeDataSet.getUser(idUser)
    }
    

    fun setUserData(user: User) {
        alkeDataSet.updateUser(user.userId, user)
    }

}