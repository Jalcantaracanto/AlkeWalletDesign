package com.example.alkewallet.feature.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alkewallet.feature.data.model.User
import com.example.alkewallet.feature.data.model.Wallet
import com.example.alkewallet.feature.domain.AlkeUseCase

<<<<<<< Updated upstream
class AlkeViewModel : ViewModel() {
    private val listDataUser = MutableLiveData<MutableList<User>>()
    val alkeUseCase = AlkeUseCase()

    private val _isUserLoggedIn = MutableLiveData<Boolean>()
    val isUserLoggedIn: LiveData<Boolean> = _isUserLoggedIn

    private val _userLogin = MutableLiveData<User>()
    val userLogin: LiveData<User> = _userLogin

    fun setListUserData(listUser: MutableList<User>) {
        listDataUser.value = listUser
    }

    fun getUserList() {
        setListUserData(alkeUseCase.getAllUsers())
=======
class AlkeViewModel: ViewModel() {
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

    fun setUserLogIn(user: User) {
        _userLogIn.value = user
>>>>>>> Stashed changes
    }

    fun addUsuario(user: User) {
        val currentList = _usuarios.value ?: mutableListOf()
        currentList.add(user)
        _usuarios.value = currentList
    }

    fun authUser(email: String, password: String): User? {
        return _usuarios.value?.find { it.userEmail == email && it.userPassword == password }
    }

//    fun actualizarSaldoUsuario(monto: Double): Boolean {
//        _usuarioLogueado.value?.let { usuario ->
//            val nuevoSaldo = usuario.saldo - monto
//            return if (nuevoSaldo >= 0) {
//                val usuarioActualizado = usuario.copy(saldo = nuevoSaldo)
//                _usuarioLogueado.value = usuarioActualizado
//
//                /* // Actualizar la lista de usuarios
//                 _usuarios.value = _usuarios.value?.map {
//                     if (it.email == usuario.email) usuarioActualizado else it
//                 }?.toMutableList()*/
//                true
//            } else {
//                false
//            }
//        }
//        return false
//    }


    fun getLoginUser(email: String, password: String) {
        val isLoginSuccessful = alkeUseCase.login(email, password)
        _isUserLoggedIn.value = isLoginSuccessful

    }

    fun getLiveDataObserver(): LiveData<MutableList<User>> {
        return listDataUser
    }

}