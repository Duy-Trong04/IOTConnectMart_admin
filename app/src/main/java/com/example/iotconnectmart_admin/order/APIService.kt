package com.example.iotconnectmart_admin.order

import com.example.iotconnectmart_admin.Customer
import com.example.iotconnectmart_admin.Order
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

data class orderResponse (
    val order : List<Order>
)

data class customerResponse (
    val success: Boolean,
    val message: String
)


interface APIService {
    @GET("order/read.php")
    suspend fun getAllOrders(): orderResponse

    @GET("order/show.php")
    suspend fun getOrderById(@Query("id") id: Int): Order

    @PUT("order/update.php")
    suspend fun updateOrder(
        @Body order: Order
    ): orderResponse

    @DELETE("order/delete.php")
    suspend fun deleteOrder(
        @Body idOrder: Int
    ): orderResponse

    @GET("customer/read.php")
    suspend fun getAllCustomers(): List<Customer>

    @GET("customer/show.php")
    suspend fun getCustomerById(@Query("id") id: String): Customer

    @POST("customer/create.php")
    suspend fun createCustomer(
        @Body customer: Customer
    ): customerResponse

    @PUT("customer/update.php")
    suspend fun updateCustomer(
        @Body customer: Customer
    ): customerResponse



//    @GET ("device/getDevicePriceThan5M.php")
//    suspend fun getDeviceFeatured(): List<Device>
//
//
//    @GET("device/getDeviceByCart.php")
//    suspend fun getDeviceByCart(
//        @Query("idCustomer") idCustomer: String
//    ): DeviceResponse
}

