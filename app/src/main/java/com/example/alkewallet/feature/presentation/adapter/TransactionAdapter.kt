package com.example.alkewallet.feature.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alkewallet.R
import com.example.alkewallet.databinding.TransferenciaItemBinding
import com.example.alkewallet.feature.data.model.Transaction
import com.example.alkewallet.feature.data.model.Transferencia
import com.example.alkewallet.feature.data.model.User
import com.example.alkewallet.feature.domain.AlkeUseCase
import com.example.alkewallet.feature.domain.TransactionUseCase

class TransactionAdapter(private val user: User) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    //Atribute

    private var transactions = TransactionUseCase().getAllTransaction()
        .filter { it.idReceiver == user.userId || it.idSender == user.userId }

    private var AlkeUseCase = AlkeUseCase()


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
            newTransactions.filter { it.idReceiver == user.userId || it.idSender == user.userId }
        notifyDataSetChanged()
    }

    inner class TransactionViewHolder(private var bindingItem: TransferenciaItemBinding) :
        RecyclerView.ViewHolder(bindingItem.root) {

        fun bind(transaction: Transaction) {
            var userID = 0L
            if (transaction.idReceiver != user.userId) {
                userID = transaction.idReceiver
            } else {
                userID = transaction.idSender
            }
            val userName = AlkeUseCase.getUser(userID)
            if (userName != null) {
                bindingItem.txtnombrereceptor.text = userName.userName + " " + userName.userLastName
            }
            bindingItem.txtfecha.text = transaction.date

            if (transaction.idReceiver == user.userId) {
                bindingItem.receiverarrow.visibility = View.VISIBLE
                bindingItem.txtcantidad.text = "+$" + String.format("%.2f", transaction.balance)
            } else {
                bindingItem.senderarrow.visibility = View.VISIBLE
                bindingItem.txtcantidad.text = "-$" + String.format("%.2f", transaction.balance)
            }
            bindingItem.imageprofile.setImageResource(getImageResource(transaction.imgUser))
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


}