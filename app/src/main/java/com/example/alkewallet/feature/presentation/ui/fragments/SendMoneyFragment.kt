package com.example.alkewallet.feature.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import com.example.alkewallet.R
import com.example.alkewallet.databinding.FragmentSendMoneyBinding


class SendMoneyFragment : Fragment() {

    private lateinit var binding: FragmentSendMoneyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentSendMoneyBinding.inflate(inflater, container, false)
        return binding.root

    }
   
}