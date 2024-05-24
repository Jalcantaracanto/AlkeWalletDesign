package com.example.alkewallet.feature.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.alkewallet.R
import com.example.alkewallet.databinding.FragmentSendMoneyBinding
import com.example.alkewallet.databinding.FragmentSignupPageBinding
import com.example.alkewallet.feature.data.model.User
import com.example.alkewallet.feature.data.model.Wallet
import com.example.alkewallet.feature.presentation.viewmodel.AlkeViewModel
import com.example.alkewallet.feature.presentation.viewmodel.TransactionViewModel

class SignupPageFragment : Fragment() {

    private lateinit var binding: FragmentSignupPageBinding
    private val alkeViewModel: AlkeViewModel by activityViewModels()
    private val transactionViewModel: TransactionViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupPageBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(view)
        binding.txtHaveAccount.setOnClickListener { navController.navigate(R.id.loginPageFragment) }
        binding.btnCreateAccount.setOnClickListener { registerAccount() }
    }

    /**
     * Método para registrar un nuevo usuario en la aplicación.
     */
    fun registerAccount() {
        val name = binding.txtName.editText?.text.toString()
        val lastname = binding.txtLastname.editText?.text.toString()
        val email = binding.txtEmail.editText?.text.toString()
        val password = binding.txtPassword.editText?.text.toString()
        val confirmPassword = binding.txtPasswordCheck.editText?.text.toString()

        var isValid = true

        if (name.isEmpty()) {
            binding.txtName.editText?.error = "Ingrese su nombre"
            isValid = false
        } else {
            binding.txtName.editText?.error = null
        }

        if (lastname.isEmpty()) {
            binding.txtLastname.editText?.error = "Ingrese su apellido"
            isValid = false
        } else {
            binding.txtLastname.editText?.error = null
        }

        if (email.isEmpty()) {
            binding.txtEmail.editText?.error = "Ingrese su email"
            isValid = false
        } else {
            binding.txtEmail.editText?.error = null
        }

        if (password.isEmpty()) {
            binding.txtPassword.editText?.error = "Ingrese su contraseña"
            isValid = false
        } else {
            binding.txtPassword.editText?.error = null
        }

        if (confirmPassword.isEmpty()) {
            binding.txtPasswordCheck.editText?.error = "Confirme su contraseña"
            isValid = false
        } else {
            binding.txtPasswordCheck.editText?.error = null
        }

        if (password != confirmPassword) {
            binding.txtPasswordCheck.editText?.error = "Las contraseñas no coinciden"
            isValid = false
        } else if (confirmPassword.isNotEmpty()) {
            binding.txtPasswordCheck.editText?.error = null
        }

        if (isValid) {
            val newUserId = (alkeViewModel.users.value?.maxOfOrNull { it.userId } ?: 0) + 1
            val newWalletId = alkeViewModel.getLastWalletId() + 1
            val newWallet = Wallet(newWalletId, 1000.00)
            val newUser = User(newUserId, name, lastname, email, password, newWallet)

            alkeViewModel.addUser(newUser)

            Toast.makeText(requireContext(), "Registro exitoso", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.loginPageFragment)
            Log.d("TESTING", "USUARIOS: ${alkeViewModel.users.value}")
            Log.d("TESTING", "USUARIOS: ${transactionViewModel.transactions.value}")
        }
    }
}