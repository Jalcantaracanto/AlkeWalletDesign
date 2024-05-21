package com.example.alkewallet.feature.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
<<<<<<< Updated upstream
import com.example.alkewallet.R

class SendMoneyFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
    }
=======
import androidx.navigation.fragment.navArgs
import com.example.alkewallet.databinding.FragmentSendMoneyBinding


class SendMoneyFragment : Fragment() {

    private val args: HomePageFragmentArgs by navArgs()
    private lateinit var binding: FragmentSendMoneyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
>>>>>>> Stashed changes

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
<<<<<<< Updated upstream
        return inflater.inflate(R.layout.fragment_send_money, container, false)
=======
        binding = FragmentSendMoneyBinding.inflate(inflater, container, false)
        return binding.root
>>>>>>> Stashed changes
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

<<<<<<< Updated upstream
        val navController = findNavController(view)

        val arrow_back = view.findViewById<ImageView>(R.id.arrow_back)

        arrow_back.setOnClickListener { v: View? -> navController.navigate(R.id.homePageFragment) }
=======
        binding.btnSendMoney.setOnClickListener {
            val amount = binding.txtIngreso.editText?.text.toString()
            if (amount.isEmpty()) {
                binding.txtIngreso.error = "Ingrese un monto"
            }
        }

>>>>>>> Stashed changes
    }
}