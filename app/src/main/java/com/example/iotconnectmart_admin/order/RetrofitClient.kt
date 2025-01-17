package com.example.iotconnectmart_admin.order

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Constant{
    const val BASE_URL = "http://10.0.2.2/IOT_ConnectMart_API/api/"
}

object RetrofitClient {
    val OrderAPIService: APIService by lazy {
        Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(APIService::class.java)
    }

}