package com.example.alkewallet.feature.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.alkewallet.R
import com.example.alkewallet.feature.data.model.User

/**
 * Este Adaptador se encarga de mostrar una lista de Usuarios en un Spinner
 */
class ContactsAdapter(context: Context, private val items: List<User>) :
    ArrayAdapter<User>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createViewFromResource(position, convertView, parent, R.layout.contact_item)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createViewFromResource(position, convertView, parent, R.layout.contact_item)
    }

    private fun createViewFromResource(position: Int, convertView: View?, parent: ViewGroup, resource: Int): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(resource, parent, false)
        val item = getItem(position)

        val nameTextView = view.findViewById<TextView>(R.id.txt_name_contact)
        val emailTextView = view.findViewById<TextView>(R.id.txt_email_contact)
        val imgProfile = view.findViewById<ImageView>(R.id.img_profile_contact)

        item?.let {

            nameTextView.text = it.userName + " " + it.userLastName
            emailTextView.text = it.userEmail
            imgProfile.setImageResource(getImageResource(it.imgUser))
        }
        return view
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