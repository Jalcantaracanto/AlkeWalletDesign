package com.example.alkewallet.feature.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.alkewallet.feature.data.model.User
import com.example.alkewallet.feature.domain.AlkeUseCase

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
    }


    fun getLoginUser(email: String, password: String) {
        val isLoginSuccessful = alkeUseCase.login(email, password)
        _isUserLoggedIn.value = isLoginSuccessful

    }

    fun getLiveDataObserver(): LiveData<MutableList<User>> {
        return listDataUser
    }

}