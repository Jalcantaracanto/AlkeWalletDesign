package com.example.alkewallet.feature.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alkewallet.R
import com.example.alkewallet.feature.presentation.adapter.TransferenciaAdapter
import com.example.alkewallet.databinding.FragmentHomePageBinding
import com.example.alkewallet.feature.data.model.Transferencia
import com.example.alkewallet.feature.presentation.viewmodel.AlkeViewModel


class HomePageFragment : Fragment() {

    private lateinit var binding: FragmentHomePageBinding

    private val alkeViewModel: AlkeViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomePageBinding.inflate(inflater, container, false)

        initAdapter()


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController(view)

        val btn_request = view.findViewById<Button>(R.id.btn_requestMoney)
        val btn_send = view.findViewById<Button>(R.id.btn_sendMoney)

        btn_request.setOnClickListener { v: View? -> navController.navigate(R.id.requestMoneyFragment) }
        btn_send.setOnClickListener { v: View? -> navController.navigate(R.id.sendMoneyFragment) }


        alkeViewModel.userLogIn.observe(viewLifecycleOwner) { user ->
            binding.txtName.text = "Hola ${user.userName}!"
            binding.txtBalance.text = String.format("$%.2f", user.wallet.balance)
            val transactionAdapter = TransactionAdapter(user)
            binding.recyclerTransferencias.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerTransferencias.adapter = transactionAdapter
        }

    }
    }

    fun initAdapter() {

        val linearLayoutManager = LinearLayoutManager(requireContext())
        binding.recyclerTransferencias.layoutManager = linearLayoutManager

        val transferenciaAdapter = TransferenciaAdapter()
        transferenciaAdapter.transferencias = Transferencia.dataTransferencias
        binding.recyclerTransferencias.adapter = transferenciaAdapter





        btn_request.setOnClickListener { navController.navigate(R.id.requestMoneyFragment) }
        btn_send.setOnClickListener {
        }



    override fun onResume() {
        super.onResume()

    }


}