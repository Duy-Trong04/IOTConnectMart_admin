package com.example.iotconnectmart_admin.order

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iotconnectmart_admin.Order
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
/** ViewModel (OrderViewModel)
 * -------------------------------------------
 * Người code:Nguyen Manh CUong
 * Ngày viết: 14/1/2025
 * Lần cập nhật cuối cùng: 14/1/2025
 * -------------------------------------------
 * Input:
 *
 * Output:
 * ------------------------------------------------------------
 * Người cập nhật:
 * Ngày cập nhật:
 * ------------------------------------------------------------
 * Nội dung cập nhật:
 *
 */
class OrderViewModel: ViewModel() {

    var listAllOrder: List<Order> by mutableStateOf(emptyList())
    private val _order = MutableLiveData<Order>()
    //val order: LiveData<Order> get() = _order
    var order: Order by mutableStateOf(
        Order(0, "", "", "", "", "", "",
            "", "", "", "", "", "",
            "", 1)
    )
    fun getAllOrder(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                listAllOrder = RetrofitClient.OrderAPIService.getAllOrders()
                Log.d("OrderViewModel", "Lấy danh sách đơn hàng thành công")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("OrderViewModel", "Lỗi khi lấy danh sách đơn hàng")
            }
        }
    }

    fun getOrderById(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                var result = RetrofitClient.OrderAPIService.getOrderById(id)
                //_order.postValue(result)
                order= result
                Log.d("OrderViewModel", "Lấy đơn hàng thành công"+result.toString())
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("OrderViewModel", "Lỗi khi lấy đơn hàng")
            }
        }
    }
}
