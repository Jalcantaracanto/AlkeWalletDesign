package com.example.alkewallet.feature.domain

import android.util.Log
import com.example.alkewallet.feature.data.local.AlkeDataSet
import com.example.alkewallet.feature.data.model.User

class AlkeUseCase(private val alkeDataSet: AlkeDataSet = AlkeDataSet()) {

    /**
     * Llama a todos los usuarios a travez de AlkedataSet el cual se inyecta en el constructor
     */
    fun getAllUsers(): MutableList<User> {
        return alkeDataSet.getAllUsers().toMutableList()
    }

    /**
     * Llama a un usuario a travez de AlkedataSet el cual se inyecta en el constructor
     * @param id Long id del usuario a buscar
     */
    fun getUser(id: Long): User {
        return alkeDataSet.getUser(id)
    }

    /**
     * Llama la funcion loginUser de AlkedataSet el cual se inyecta en el constructor
     * @param email String email del usuario a buscar
     * @param password String password del usuario a buscar
     */
    fun authUser(email: String, password: String): User? {
        return alkeDataSet.loginUser(email, password)
    }

    /**
     * Busca el ultimo id del usuario registrado llamando la funcion getALlUsers
     */
    fun getLastUserId(): Long {
        return alkeDataSet.getAllUsers().maxOfOrNull { it.userId } ?: 0
    }

    /**
     * Busca el ultimo id de wallet registrado llamando la funcion getALlUsers y filtrando por el ultimo id
     */
    fun getLastWalletId(): Long {
        return alkeDataSet.getAllUsers().maxOfOrNull { it.wallet.idWallet } ?: 0
    }

    /**
     *  Llama la funcion addUser de AlkedataSet el cual se inyecta en el constructor
     *  @param user User - Se manda usuario para ser agregado a la lista
     */
    fun addUser(user: User) {
        alkeDataSet.users.add(user)
    }

    fun updateUser(userId: Long, updatedUser: User): User {
        return alkeDataSet.updateUser(userId, updatedUser)
    }
}