package com.example.alkewallet.feature.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.alkewallet.R
import com.example.alkewallet.databinding.FragmentProfilePageBinding
import com.example.alkewallet.feature.presentation.viewmodel.AlkeViewModel


class ProfilePageFragment : Fragment() {

    private lateinit var binding: FragmentProfilePageBinding
    private val alkeViewModel: AlkeViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfilePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        alkeViewModel.userLogIn.observe(viewLifecycleOwner) { user ->
            binding.txtProfileName.text = user.userName + " " + user.userLastName
            binding.imageProfile.setImageResource(getImageResource(user.imgUser))
        }

    }

    private fun getImageResource(imageName: String): Int {
        return when (imageName) {
            "pp1" -> R.drawable.pp1
            "pp2" -> R.drawable.pp2
            "pp3" -> R.drawable.pp3
            "pp4" -> R.drawable.pp4
            "pp5" -> R.drawable.pp5

            else -> R.drawable.pp_empty
        }
    }

}