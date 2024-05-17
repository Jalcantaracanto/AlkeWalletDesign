package com.example.alkewallet.feature.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import com.example.alkewallet.R
import com.example.alkewallet.databinding.FragmentLoginPageBinding
import com.example.alkewallet.feature.data.model.User
import com.example.alkewallet.feature.presentation.viewmodel.AlkeViewModel

class LoginPageFragment : Fragment() {

    private lateinit var binding: FragmentLoginPageBinding
    private lateinit var viewModelAlke: AlkeViewModel
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

        setUpViewModel()

        binding.btnLogin.setOnClickListener {
            val email = binding.textInputEmail.editText?.text.toString()
            val password = binding.textInputPassword.editText?.text.toString()

            viewModelAlke.getLoginUser(email, password)
        }
    }

    private fun setUpViewModel() {

        viewModelAlke = ViewModelProvider(this)[AlkeViewModel::class.java]

        val alkeObserver = Observer<MutableList<User>> {
        }
        viewModelAlke.getLiveDataObserver().observe(viewLifecycleOwner, alkeObserver)

        viewModelAlke.isUserLoggedIn.observe(viewLifecycleOwner, Observer { isLoggedIn ->
            if (isLoggedIn) {
                Log.i("TESTLOGIN", "Usuario Existe")
                Toast.makeText(requireContext(), "Credenciales Correctas", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Log.i("TESTLOGIN", "Usuario No Existe")
                Toast.makeText(
                    requireContext(),
                    "Usuario y/o contraseña incorrectas",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        })
    }
}