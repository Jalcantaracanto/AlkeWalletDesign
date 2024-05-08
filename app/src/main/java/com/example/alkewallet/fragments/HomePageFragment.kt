package com.example.alkewallet.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import com.example.alkewallet.R

class HomePageFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController(view)

        val btn_request = view.findViewById<Button>(R.id.btn_requestMoney)
        val btn_send = view.findViewById<Button>(R.id.btn_sendMoney)

        btn_request.setOnClickListener { v: View? -> navController.navigate(R.id.requestMoneyFragment) }
        btn_send.setOnClickListener { v: View? -> navController.navigate(R.id.sendMoneyFragment) }
    }
}