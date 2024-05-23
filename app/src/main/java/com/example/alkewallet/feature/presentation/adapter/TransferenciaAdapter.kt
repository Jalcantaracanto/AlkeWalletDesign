package com.example.alkewallet.feature.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.alkewallet.R
import com.example.alkewallet.databinding.TransferenciaItemBinding
import com.example.alkewallet.feature.data.model.Transferencia

private val TAG = TransferenciaAdapter::class.java.simpleName
class TransferenciaAdapter : RecyclerView.Adapter<TransferenciaAdapter.TransferenciaViewHolder>() {

    //Atributo
    var transferencias = mutableListOf<Transferencia>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransferenciaViewHolder {

        val bindingItem =
            TransferenciaItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TransferenciaViewHolder(bindingItem)
    }

    override fun onBindViewHolder(
        holder: TransferenciaViewHolder,
        position: Int
    ) {
        val transferencia: Transferencia = transferencias[position]
        holder.bind(transferencia)
    }

    override fun getItemCount(): Int {
        return transferencias.size
    }

    inner class TransferenciaViewHolder(private var bindingItem: TransferenciaItemBinding) :
        RecyclerView.ViewHolder(bindingItem.root) {

        fun bind(transferencia: Transferencia) {
            bindingItem.txtnombrereceptor.text = transferencia.nombre
            bindingItem.txtfecha.text = transferencia.fecha


            if (transferencia.esReceptor) {
                bindingItem.receiverarrow.visibility = View.VISIBLE
                bindingItem.txtcantidad.text = "+$" + String.format("%.2f", transferencia.monto)
            } else {
                bindingItem.senderarrow.visibility = View.VISIBLE
                bindingItem.txtcantidad.text = "-$" + String.format("%.2f", transferencia.monto)
            }

            bindingItem.imageprofile.setImageResource(getImageResource(transferencia.imgUser))

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