package com.example.alkewallet.feature.data.model

data class Transferencia(
    val id: String,
    val nombre: String,
    val fecha: String,
    val monto: Double,
    val esReceptor: Boolean,
    val imgUser: String,
) {
    companion object {
        val dataTransferencias = mutableListOf<Transferencia>(
            Transferencia(id = "1", nombre = "Juan Rivera", fecha = "Oct 14, 10:24 AM", monto = 20.43, esReceptor = true, imgUser = "pp1"),
            Transferencia(id = "2", nombre = "Javiera Suniga", fecha = "Oct 15, 11:24 AM", monto = 35.00, esReceptor = false, imgUser = "pp2"),

        )

        val dataEmpty = mutableListOf<Transferencia>()
    }
}