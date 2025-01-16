package com.example.iotconnectmart_admin.customer

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iotconnectmart_admin.Customer
import com.example.iotconnectmart_admin.Order
import com.example.iotconnectmart_admin.order.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CustomerViewModel : ViewModel(){

    var listAllCustomer: List<Customer> by mutableStateOf(emptyList())
    var customer by mutableStateOf(
        Customer("","","","","","",0,"","",1)
    )
    var customerResult by mutableStateOf("")

    fun getAllCustomers(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                listAllCustomer = RetrofitClient.OrderAPIService.getAllCustomers()
                Log.d("CustomerViewModel", "Lấy danh sách Khach hang thành công")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("CustomerViewModel", "Lỗi khi lấy danh sách Khach hang")
            }
        }
    }

    fun getCustomerById(id: String){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                var result = RetrofitClient.OrderAPIService.getCustomerById(id)
                customer= result
                Log.d("CustomerViewModel", "Lấy  Khach hang thành công")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("CustomerViewModel", "Lỗi khi lấy Khach hang")
            }
        }
    }

    suspend fun createCustomer(customerNew: Customer){
        try {
            val response = withContext(Dispatchers.IO) {
                RetrofitClient.OrderAPIService.createCustomer(customerNew)
            }
            customerResult = if (response.success) {
                "Cập nhật thành công"
            } else {
                "Cập nhật thất bại"
            }
            Log.d("orderError", "cập nhật order thanh cong:"+customerResult)

        } catch (e: Exception) {
            customerResult = "Lỗi khi cập nhật order: ${e.message}"
            Log.d("orderError", "Lỗi khi cập nhật order: ${e.message}")
        }
    }

    fun updateCustomer(customerUPdate: Customer){
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitClient.OrderAPIService.updateCustomer(customerUPdate)
                }
                customerResult = if (response.success) {
                    "Cập nhật thành công"
                } else {
                    "Cập nhật thất bại"
                }
                Log.d("customerError", "cập nhật order thanh cong:"+customerResult)

            } catch (e: Exception) {
                customerResult = "Lỗi khi cập nhật khach hang: ${e.message}"
                Log.d("customerError", "Lỗi khi cập nhật khach hang: ${e.message}")
            }
        }
    }

}