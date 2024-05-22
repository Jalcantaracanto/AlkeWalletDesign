package com.example.alkewallet.feature.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.alkewallet.R
import com.example.alkewallet.databinding.FragmentLoginPageBinding
import com.example.alkewallet.feature.data.model.User

import com.example.alkewallet.feature.presentation.viewmodel.AlkeViewModel

class LoginPageFragment : Fragment() {

    private lateinit var binding: FragmentLoginPageBinding

    private lateinit var viewModelAlke: AlkeViewModel

    private val alkeViewModel: AlkeViewModel by activityViewModels()


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
        binding.txtRegister.setOnClickListener{ navController.navigate(R.id.signupPageFragment) }
    }

    fun emailCheck() {

        val txtEmail = binding.textInputEmail.editText?.text.toString()
        val txtPassword = binding.textInputPassword.editText?.text.toString()

        val user = alkeViewModel.authUser(txtEmail, txtPassword)

        if (user != null) {
            alkeViewModel.setUserLogIn(user)
            findNavController().navigate(R.id.homePageFragment)
        } else {
            Toast.makeText(requireContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT)
                .show()
        }
    }

}