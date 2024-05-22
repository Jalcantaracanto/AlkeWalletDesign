package com.example.alkewallet.feature.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alkewallet.feature.data.local.AlkeDataSet
import com.example.alkewallet.feature.data.model.User
import com.example.alkewallet.feature.data.model.Wallet
import com.example.alkewallet.feature.domain.AlkeUseCase

class AlkeViewModel : ViewModel() {

    private val alkeUseCase = AlkeUseCase()

    private val _usuarios = MutableLiveData<MutableList<User>>()
    val users: LiveData<MutableList<User>> get() = _usuarios

    private val _userLogIn = MutableLiveData<User>()
    val userLogIn: LiveData<User> get() = _userLogIn

    init {
        _usuarios.value = alkeUseCase.getAllUsers()
    }

    fun getUser(id: Long): User? {
        return alkeUseCase.getUser(id)
    }

    fun setUserLogIn(user: User) {
        _userLogIn.value = user
    }

    fun addUser(user: User) {
        alkeUseCase.addUser(user)
        _usuarios.value = alkeUseCase.getAllUsers()
    }

    fun authUser(email: String, password: String): User? {
        return alkeUseCase.authUser(email, password)
    }

    fun updateBalanceUser(monto: Double, isSend: Boolean): Boolean {
        _userLogIn.value?.let { user ->
            if (alkeUseCase.updateBalanceUser(user, monto, isSend)) {
                _userLogIn.value = alkeUseCase.getUser(user.userId)
                _usuarios.value = alkeUseCase.getAllUsers()
                return true
            }
        }
        return false
    }

    fun getLastUserId(): Long {
        return alkeUseCase.getLastUserId()
    }

    fun getLastWalletId(): Long {
        return alkeUseCase.getLastWalletId()
    }
}