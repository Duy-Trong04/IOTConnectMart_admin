package com.example.iotconnectmart_admin.order

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
    var order: MutableLiveData<Order?> = MutableLiveData()
    //var listAllOrder: List<Order> by mutableStateOf(emptyList())

    //var listDeviceFeatured: List<Order> by mutableStateOf(emptyList())

    //var order:Order by mutableStateOf(Order (0, "", "", "","", "", 0.0, 0, "", "", 0,0))

        //var listDeviceOfCustomer by mutableStateOf<List<Order>>(emptyList())
        //private set

    // Dữ liệu tìm kiếm
    //var searchQuery: String by mutableStateOf("")
    // var searchHistory: MutableList<String> by mutableStateOf(mutableStateListOf())
    //var searchResult: List<Order> by mutableStateOf(emptyList())

    fun getAllOrder(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                listAllOrder = RetrofitClient.OrderAPIService.getAllOrders()
                Log.d("aaaaaaaaa","thanh cong")
            } catch (e: Exception) {
                e.printStackTrace() // Xử lý lỗi
                Log.d("aaaaaaaaa","sai")
            }
        }
    }
    fun getOrderById(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val orderResult = RetrofitClient.OrderAPIService.getOrderById(id)
                order.postValue(orderResult)// Cập nhật giá trị từ luồng nền
                Log.d("aaaaaaaaa","thanh cong")
            } catch (e: Exception) {
                e.printStackTrace() // Xử lý lỗi
                order.postValue(null)// Cập nhật giá trị null trong trường hợp lỗi
                Log.d("aaaaaaaaa","sai")
            }
        }
    }
//    fun getDeviceByCart(idCustomer: String) {
//        viewModelScope.launch {
//            try {
//                val response = withContext(Dispatchers.IO) {
//                    RetrofitClient.deviceAPIService.getDeviceByCart(idCustomer)
//                }
//                listDeviceOfCustomer = response.device
//            } catch (e: Exception) {
//                Log.e("Device Error", "Lỗi khi lấy sản phẩm: ${e.message}")
//            }
//        }
//    }

//    fun getDeviceFeatured(){
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                listDeviceFeatured = RetrofitClient.deviceAPIService.getDeviceFeatured()
//                Log.d("aaaaaaaaa","thanh cong")
//            } catch (e: Exception) {
//                e.printStackTrace() // Xử lý lỗi
//                Log.d("aaaaaaaaa","sai")
//            }
//        }
//    }
//
//    fun getDeviceBySlug(id:String){
//        viewModelScope.launch (Dispatchers.IO){
//            try {
//                device = RetrofitClient.deviceAPIService.getDeviceById(id)
//                searchResult = listAllDevice // Hiển thị toàn bộ danh sách ban đầu
//            }
//            catch (e:Exception){
//                Log.e("DeviceViewModel", "Error getting device", e)
//            }
//        }
//    }
//    // Tìm kiếm thiết bị và lưu vào lịch sử khi nhấn nút tìm kiếm
//    fun searchDevice(query: String) {
//        searchQuery = query
//        if (query.isNotEmpty()) {
//            val filteredDevices = listAllDevice.filter {
//                it.name.contains(query.trim(), ignoreCase = true) || it.descriptionNormal.contains(query.trim(), ignoreCase = true)
//            }
//            searchResult = filteredDevices
//
//            // Thêm từ khóa vào lịch sử tìm kiếm nếu chưa có
//            if (!searchHistory.contains(query)) {
//                searchHistory.add(query)
//            }
//        } else {
//            // Khi không có từ khóa tìm kiếm, hiển thị tất cả thiết bị
//            searchResult = listAllDevice
//        }
//    }
//
//    // Xóa lịch sử tìm kiếm
//    fun removeSearchHistory(keyword: String) {
//        searchHistory.remove(keyword)
//    }
//
//    // Xóa toàn bộ lịch sử tìm kiếm
//    fun clearSearchHistory() {
//        searchHistory.clear()
//    }
}