package com.example.alkewallet.feature.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.alkewallet.R
import com.example.alkewallet.databinding.FragmentLoginPageBinding
import com.example.alkewallet.feature.data.model.User

import com.example.alkewallet.feature.presentation.viewmodel.AlkeViewModel
import com.example.alkewallet.feature.presentation.viewmodel.TransactionViewModel

class LoginPageFragment : Fragment() {

    private lateinit var binding: FragmentLoginPageBinding

    private val alkeViewModel: AlkeViewModel by activityViewModels()
    private val transactionViewModel: TransactionViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginPageBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController(view)


        binding.btnLogin.setOnClickListener { emailCheck() }
        binding.txtRegister.setOnClickListener { navController.navigate(R.id.signupPageFragment) }

        Log.d("TESTING", "USUARIOS: ${alkeViewModel.users.value}")
        Log.d("TESTING", "USUARIOS: ${transactionViewModel.transactions.value}")
    }

    /**
     * Esta funcion se encarga de validar el email y la contraseña del usuario
     */
    fun emailCheck() {

        val txtEmail = binding.textInputEmail.editText?.text.toString()
        val txtPassword = binding.textInputPassword.editText?.text.toString()

        val user = alkeViewModel.authUser(txtEmail, txtPassword)

        if (user != null) {
            alkeViewModel.setUserLogIn(user)
            transactionViewModel.getLastTransactionId()
            findNavController().navigate(R.id.homePageFragment)
        } else {
            Toast.makeText(requireContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT)
                .show()
        }
    }

}