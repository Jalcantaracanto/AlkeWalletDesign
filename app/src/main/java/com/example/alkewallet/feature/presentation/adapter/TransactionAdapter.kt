package com.example.alkewallet.feature.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.alkewallet.R
import com.example.alkewallet.databinding.TransferenciaItemBinding
import com.example.alkewallet.feature.data.model.Transaction
import com.example.alkewallet.feature.data.model.User
import com.example.alkewallet.feature.domain.AlkeUseCase
import com.example.alkewallet.feature.domain.TransactionUseCase
import com.example.alkewallet.feature.presentation.viewmodel.AlkeViewModel

/**
 * Adaptador que se encarga de pintar el RecyclerView al cual se le inyecta un usuario, con esto
 * se realiza un filtro para obtener solo las transacciones pertenecientes al usuario conectado.
 */
class TransactionAdapter(private val alkeViewModel: AlkeViewModel) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    //Atribute

    var transactions = TransactionUseCase().getAllTransaction()
        .filter { it.idReceiver == alkeViewModel.userLogIn.value?.userId || it.idSender == alkeViewModel.userLogIn.value?.userId }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionAdapter.TransactionViewHolder {

        val bindingItem =
            TransferenciaItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TransactionViewHolder(bindingItem)
    }

    override fun onBindViewHolder(holder: TransactionAdapter.TransactionViewHolder, position: Int) {
        val transaction: Transaction = transactions[position]
        holder.bind(transaction)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    fun updateTransactions(newTransactions: List<Transaction>) {
        transactions =
            newTransactions.filter { it.idReceiver == alkeViewModel.userLogIn.value?.userId || it.idSender == alkeViewModel.userLogIn.value?.userId }
        notifyDataSetChanged()
    }

    inner class TransactionViewHolder(private var bindingItem: TransferenciaItemBinding) :
        RecyclerView.ViewHolder(bindingItem.root) {

        fun bind(transaction: Transaction) {
            val user = alkeViewModel.userLogIn.value
            var userID = 0L
            if (user != null) {
                if (transaction.idReceiver != user.userId) {
                    userID = transaction.idReceiver
                } else {
                    userID = transaction.idSender
                }
            }
            alkeViewModel.getUser(userID)
            val userName = alkeViewModel.userTransaction.value
            if (userName != null) {
                bindingItem.txtnombrereceptor.text = userName.userName + " " + userName.userLastName
            }
            bindingItem.txtfecha.text = transaction.date

            if (user != null) {
                if (transaction.idReceiver == user.userId) {
                    bindingItem.receiverarrow.visibility = View.VISIBLE
                    bindingItem.txtcantidad.text = "+$" + String.format("%.2f", transaction.balance)
                } else {
                    bindingItem.senderarrow.visibility = View.VISIBLE
                    bindingItem.txtcantidad.text = "-$" + String.format("%.2f", transaction.balance)
                }
            }
            bindingItem.imageprofile.setImageResource(getImageResource(transaction.imgUser))
        }

        /**
         * Funcion para asignar la imagen del perfil a travez de un String
         */
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


}