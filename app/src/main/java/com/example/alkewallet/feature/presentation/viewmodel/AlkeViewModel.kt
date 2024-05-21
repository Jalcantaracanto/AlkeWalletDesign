package com.example.alkewallet.feature.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alkewallet.feature.data.model.User
import com.example.alkewallet.feature.data.model.Wallet
import com.example.alkewallet.feature.domain.AlkeUseCase

class AlkeViewModel : ViewModel() {
    private val _usuarios = MutableLiveData<MutableList<User>>()
    val users: LiveData<MutableList<User>> get() = _usuarios

    private val _userLogIn = MutableLiveData<User>()
    val userLogIn: LiveData<User> get() = _userLogIn

    init {
        // Inicializa con una lista vacía o con datos iniciales
        _usuarios.value = mutableListOf(
            User(100, "Javier", "Alcantara", "18298640-2", "123", "12345", Wallet(100, 100000.00))
        )
    }

    fun getUser(id: Long): User? {
        return _usuarios.value?.find { it.userId == id }
    }

    fun setUserLogIn(user: User) {
        _userLogIn.value = user
    }

    fun addUsuario(user: User) {
        val currentList = _usuarios.value ?: mutableListOf()
        currentList.add(user)
        _usuarios.value = currentList
    }

    fun authUser(email: String, password: String): User? {
        return _usuarios.value?.find { it.userEmail == email && it.userPassword == password }
    }

    fun updateBalanceUser(monto: Double): Boolean {
        _userLogIn.value?.let { user ->
            val nuevoSaldo = user.wallet.balance - monto
            return if (nuevoSaldo >= 0) {
                val nuevaWallet = user.wallet.copy(balance = nuevoSaldo)
                val usuarioActualizado = user.copy(wallet = nuevaWallet)
                _userLogIn.value = usuarioActualizado
                _usuarios.value = _usuarios.value?.map {
                    if (it.userId == user.userId) usuarioActualizado else it
                }?.toMutableList()

                true
            } else {
                false
            }
        }
        return false
    }


}