package com.example.iotconnectmart_admin.order

import com.example.iotconnectmart_admin.Order
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("order/readOrder.php")
    suspend fun getAllOrders(): List<Order>

    @GET("order/showOrder.php")
    suspend fun getOrderById(@Query("id") id: Int): Order

//    @GET ("device/getDevicePriceThan5M.php")
//    suspend fun getDeviceFeatured(): List<Device>
//
//
//    @GET("device/getDeviceByCart.php")
//    suspend fun getDeviceByCart(
//        @Query("idCustomer") idCustomer: String
//    ): DeviceResponse
}