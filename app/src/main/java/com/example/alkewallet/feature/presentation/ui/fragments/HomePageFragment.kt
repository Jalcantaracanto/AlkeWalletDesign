package com.example.alkewallet.feature.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
<<<<<<< Updated upstream
=======
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
>>>>>>> Stashed changes
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alkewallet.R
import com.example.alkewallet.feature.presentation.adapter.TransferenciaAdapter
import com.example.alkewallet.databinding.FragmentHomePageBinding
<<<<<<< Updated upstream
import com.example.alkewallet.feature.data.model.Transferencia
=======
import com.example.alkewallet.feature.presentation.adapter.TransactionAdapter
import com.example.alkewallet.feature.presentation.viewmodel.AlkeViewModel
>>>>>>> Stashed changes

class HomePageFragment : Fragment() {

    private lateinit var binding: FragmentHomePageBinding
<<<<<<< Updated upstream

=======
    private val alkeViewModel: AlkeViewModel by activityViewModels()
>>>>>>> Stashed changes

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
<<<<<<< Updated upstream
        initAdapter()

=======
>>>>>>> Stashed changes
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
        val navController = findNavController(view)

        val btn_request = view.findViewById<Button>(R.id.btn_requestMoney)
        val btn_send = view.findViewById<Button>(R.id.btn_sendMoney)

<<<<<<< Updated upstream
        btn_request.setOnClickListener { v: View? -> navController.navigate(R.id.requestMoneyFragment) }
        btn_send.setOnClickListener { v: View? -> navController.navigate(R.id.sendMoneyFragment) }
    }

    fun initAdapter() {

        val linearLayoutManager = LinearLayoutManager(requireContext())
        binding.recyclerTransferencias.layoutManager = linearLayoutManager

        val transferenciaAdapter = TransferenciaAdapter()
        transferenciaAdapter.transferencias = Transferencia.dataTransferencias
        binding.recyclerTransferencias.adapter = transferenciaAdapter

    }


=======
        btn_request.setOnClickListener { navController.navigate(R.id.requestMoneyFragment) }
        btn_send.setOnClickListener {
        }

        alkeViewModel.userLogIn.observe(viewLifecycleOwner) { user ->
            binding.txtHelloName.text = "Hola ${user.userName}!"
            binding.txtBalance.text = String.format("$%.2f", user.wallet.balance)
            val transactionAdapter = TransactionAdapter(user)
            binding.recyclerTransferencias.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerTransferencias.adapter = transactionAdapter
        }

    }

    override fun onResume() {
        super.onResume()

    }

    private fun initAdapter() {
//        homePageViewModel.user.observe(viewLifecycleOwner, Observer { user ->
//            Log.e("TESTTING", "HOMEPAGE INIT: $user")
//
//            if (user != null) {
//                binding.txtHelloName.text = "Hola ${user.userName}!"
//                binding.txtBalance.text = String.format("$%.2f", user.wallet.balance)
//
//                val transactionAdapter = TransactionAdapter(user)
//                binding.recyclerTransferencias.layoutManager = LinearLayoutManager(requireContext())
//                binding.recyclerTransferencias.adapter = transactionAdapter
//            }
//        })
    }
>>>>>>> Stashed changes
}