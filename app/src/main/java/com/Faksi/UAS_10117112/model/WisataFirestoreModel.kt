package com.Faksi.UAS_10117112.model

data class WisataFirestoreModel (
        val nama: String,
        val deskripsi: String,
        val alamat: String,
        val sumber: String,
        val harga: String,
        val lat: String,
        val lon: String,
        val phone: String,
        val foto1: String,
        val foto2: String,
        val foto3: String ) {
    constructor(): this("","","","","","","","","","","")
}