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

    private val _users = MutableLiveData<MutableList<User>>()
    val users: LiveData<MutableList<User>> get() = _users

    private val _userLogIn = MutableLiveData<User>()
    val userLogIn: LiveData<User> get() = _userLogIn

    private val _userTransaction = MutableLiveData<User>()
    val userTransaction: LiveData<User> get() = _userTransaction

    init {
        _users.value = alkeUseCase.getAllUsers()
    }

    /**
     * setea el usuario que se logueo para poder trabajar con el
     */
    fun setUserLogIn(user: User) {
        _userLogIn.value = user
    }

    /**
     * llama a la funcion del caso de uso para poder agregar un usuario y vuelve a cargar los usuarios
     */
    fun addUser(user: User) {
        alkeUseCase.addUser(user)
        _users.value = alkeUseCase.getAllUsers()
    }

    /**
     * llama a la funcion del caso de uso para poder authenticar un usuario
     */
    fun authUser(email: String, password: String): User? {
        return alkeUseCase.authUser(email, password)
    }

    /**
     * Funcion para realizar updateo del balance de usuario (FALTA AJUSTAR)
     */
    fun updateBalanceUser(amount: Double,receiver: User, isSend: Boolean): Boolean {
        _userLogIn.value?.let { user ->
            val newBalance = if (isSend) {
                user.wallet.balance - amount
            } else {
                user.wallet.balance + amount
            }
            if (newBalance >= 0 || !isSend) {
                val receiverNewBalance = receiver.wallet.balance + amount

                val updatedUser = user.copy(wallet = user.wallet.copy(balance = newBalance))
                val updatedReceiver = receiver.copy(wallet = receiver.wallet.copy(balance = receiverNewBalance))
                alkeUseCase.updateUser(user.userId, updatedUser)
                alkeUseCase.updateUser(receiver.userId, updatedReceiver)

                _userLogIn.value = updatedUser
                _userTransaction.value = updatedReceiver

                return true
            }
        }
        return false
    }

    fun getUser(id: Long) {
        val user = alkeUseCase.getUser(id)
        _userTransaction.value = user
    }


    fun getLastUserId(): Long {
        return alkeUseCase.getLastUserId()
    }

    fun getLastWalletId(): Long {
        return alkeUseCase.getLastWalletId()
    }

}