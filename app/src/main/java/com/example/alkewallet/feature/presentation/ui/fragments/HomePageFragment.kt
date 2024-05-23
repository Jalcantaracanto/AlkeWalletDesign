package com.example.alkewallet.feature.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alkewallet.R
import com.example.alkewallet.feature.presentation.adapter.TransferenciaAdapter
import com.example.alkewallet.databinding.FragmentHomePageBinding
import com.example.alkewallet.feature.data.model.Transferencia
import com.example.alkewallet.feature.domain.TransactionUseCase
import com.example.alkewallet.feature.presentation.adapter.TransactionAdapter
import com.example.alkewallet.feature.presentation.viewmodel.AlkeViewModel
import com.example.alkewallet.feature.presentation.viewmodel.TransactionViewModel


class HomePageFragment : Fragment() {

    private lateinit var binding: FragmentHomePageBinding
    private val alkeViewModel: AlkeViewModel by activityViewModels()
    private val transactionViewModel: TransactionViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomePageBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController(view)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.loginPageFragment)
                }
            })

        binding.btnRequestMoney.setOnClickListener { navController.navigate(R.id.requestMoneyFragment) }
        binding.btnSendMoney.setOnClickListener { navController.navigate(R.id.sendMoneyFragment) }
        binding.imgUserProfile.setOnClickListener { navController.navigate(R.id.profilePageFragment) }



        alkeViewModel.userLogIn.observe(viewLifecycleOwner) { user ->
            binding.imgUserProfile.setImageResource(getImageResource(user.imgUser))
            binding.txtName.text = "Hola ${user.userName}!"
            binding.txtBalance.text = String.format("$%.2f", user.wallet.balance)
            val transactionAdapter = TransactionAdapter(user)

            binding.recyclerTransferencias.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerTransferencias.adapter = transactionAdapter
            transactionViewModel.transactions.observe(viewLifecycleOwner) { transaction ->
                transactionAdapter.updateTransactions(transaction)
            }

            if (transactionAdapter.transactions.isEmpty()) {
                binding.recyclerTransferencias.visibility = View.GONE
                binding.imgEmptyTransaction.visibility = View.VISIBLE
                binding.txtEmptyTransaction.visibility = View.VISIBLE
            } else {
                binding.recyclerTransferencias.visibility = View.VISIBLE
                binding.imgEmptyTransaction.visibility = View.GONE
                binding.txtEmptyTransaction.visibility = View.GONE
            }
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