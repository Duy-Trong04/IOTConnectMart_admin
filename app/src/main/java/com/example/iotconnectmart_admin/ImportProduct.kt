package com.example.iotconnectmart_admin.data

data class ImportProduct(
    val id: String,
    val creator: String,
    val importDate: String,
    val productName: String,
    val verificationFile: String,
    val importPrice: String,
    val quantity: String,
    val totalAmount: String,
    val note: String,
    val status: String,
    val image: String
)
