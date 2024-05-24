package com.example.alkewallet.feature.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import com.example.alkewallet.R
import com.example.alkewallet.databinding.FragmentLoginSignupBinding

class LoginSignupFragment : Fragment() {

    private lateinit var binding: FragmentLoginSignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController(view)

        binding.textIHaveAccount.setOnClickListener { navController.navigate(R.id.loginPageFragment) }
        binding.btnLogin.setOnClickListener { navController.navigate(R.id.signupPageFragment) }
    }
}