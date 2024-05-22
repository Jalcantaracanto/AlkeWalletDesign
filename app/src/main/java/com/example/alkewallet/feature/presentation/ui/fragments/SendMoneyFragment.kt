package com.example.alkewallet.feature.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.alkewallet.R
import com.example.alkewallet.databinding.FragmentSendMoneyBinding
import com.example.alkewallet.feature.data.local.AlkeDataSet
import com.example.alkewallet.feature.data.model.Transaction
import com.example.alkewallet.feature.data.model.User
import com.example.alkewallet.feature.presentation.adapter.ContactsAdapter
import com.example.alkewallet.feature.presentation.viewmodel.AlkeViewModel
import com.example.alkewallet.feature.presentation.viewmodel.TransactionViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class SendMoneyFragment : Fragment() {

    private lateinit var binding: FragmentSendMoneyBinding
    private val alkeViewModel: AlkeViewModel by activityViewModels()
    private val transactionViewModel: TransactionViewModel by activityViewModels()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var userReceiverId = 0L
        var imgUser = ""
        val spinner = binding.spinnerSendMoney
        val currentUser = alkeViewModel.userLogIn.value

        // SPINNER
        val items = AlkeDataSet().getAllUsers().filter { it.userId != currentUser?.userId }
        val adapter = ContactsAdapter(requireContext(), items)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedUser = parent?.getItemAtPosition(position) as User
                userReceiverId = selectedUser.userId
                imgUser = selectedUser.imgUser
                Log.d("USER_SELECTED", "User ID: $userReceiverId")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se seleccionó ningún usuario
            }
        }

        binding.btnSendMoney.setOnClickListener {
            val amount = binding.txtAmount.editText?.text.toString().toDouble()
            val amountSuficient = alkeViewModel.updateBalanceUser(amount, true)

            if (!amountSuficient) {
                Toast.makeText(
                    requireContext(),
                    "Saldo insuficiente para realizar la transacción",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val currentDate = Date()
            val dateFormat = SimpleDateFormat("MMM dd, hh:mm a", Locale.getDefault())
            val formattedDate = dateFormat.format(currentDate)
            val lastTransactionId = transactionViewModel.getLastTransactionId()

            val newTransaction =
                currentUser?.let { it1 -> Transaction(lastTransactionId + 1, amount, formattedDate, it1.userId, userReceiverId, imgUser) }

            if (newTransaction != null) {
                transactionViewModel.addTransaction(newTransaction)
            }

            findNavController().navigate(R.id.homePageFragment)
        }
    }
}