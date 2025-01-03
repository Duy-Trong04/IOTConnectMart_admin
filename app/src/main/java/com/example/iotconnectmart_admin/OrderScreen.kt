package com.example.iotconnectmart_admin

import androidx.compose.foundation.Image
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.iotconnectmart_admin.order.OrderViewModel

/** Giao diện màn hình Danh sách Đơn hàng (OrderScreen)
 * -------------------------------------------
 * Người code: Văn Nam Cao
 * Ngày viết: 22/12/2024
 * Lần cập nhật cuối cùng: 23/12/2024
 * -------------------------------------------
 * Input:
 *
 * Output: Chứa các thành phần giao diện của màn hình danh sách Đơn hàng
 * ------------------------------------------------------------
 * Người cập nhật:Nguyen Manh Cuong
 * Ngày cập nhật:14/1/2025
 * ------------------------------------------------------------
 * Nội dung cập nhật:
 *
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(navController: NavController,viewModel: OrderViewModel) {


    viewModel.getAllOrder()
    var listAllOrder : List<Order> = viewModel.listAllOrder


    // Lấy context từ Composable
    val context = LocalContext.current
    // Kiểm tra thiết bị là tablet hay mobile
    val isTablet = isTablet(context)
    // Đặt giá trị padding
    val paddingValue = if (isTablet) 270.dp else 50.dp

    // Tạo trạng thái cho TextField
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
/*
    val dsOrder = remember {
        mutableStateOf(
            listOf(
                Order(
                    id = 1,
                    orderId = "ORD001",
                    CustomerName = "Nguyễn Văn A",
                    CustomerPhone = 123456789,
                    CustomerAddress = "123 Đường ABC, Quận 1, TP.HCM",
                    CustomerEmail = "customerA@gmail.com",
                    EmployeeName = "Trần Thị B",
                    OrderDate = "2024-12-20",
                    Notes = "Giao hàng vào buổi sáng.",
                    state = "Đang giao"
                ),
                Order(
                    id = 2,
                    orderId = "ORD002",
                    CustomerName = "Lê Thị C",
                    CustomerPhone = 987654321,
                    CustomerAddress = "456 Đường DEF, Quận 2, TP.HCM",
                    CustomerEmail = "customerC@gmail.com",
                    EmployeeName = "Nguyễn Văn D",
                    OrderDate = "2024-12-18",
                    Notes = "Liên hệ trước khi giao.",
                    state = "Chưa giao"
                ),
                Order(
                    id = 3,
                    orderId = "ORD003",
                    CustomerName = "Phạm Văn E",
                    CustomerPhone = 112233445,
                    CustomerAddress = "789 Đường GHI, Quận 3, TP.HCM",
                    CustomerEmail = "customerE@gmail.com",
                    EmployeeName = "Trần Văn F",
                    OrderDate = "2024-12-19",
                    Notes = "Khách hàng muốn kiểm tra hàng trước khi nhận.",
                    state = "Đang giao"
                ),
                Order(
                    id = 4,
                    orderId = "ORD004",
                    CustomerName = "Đặng Thị G",
                    CustomerPhone = 556677889,
                    CustomerAddress = "123 Đường JKL, Quận 4, TP.HCM",
                    CustomerEmail = "customerG@gmail.com",
                    EmployeeName = "Lê Thị H",
                    OrderDate = "2024-12-17",
                    Notes = "Khách hàng yêu cầu xuất hóa đơn.",
                    state = "Chưa giao"
                ),
                Order(
                    id = 5,
                    orderId = "ORD005",
                    CustomerName = "Võ Văn I",
                    CustomerPhone = 223344556,
                    CustomerAddress = "456 Đường MNO, Quận 5, TP.HCM",
                    CustomerEmail = "customerI@gmail.com",
                    EmployeeName = "Phạm Thị J",
                    OrderDate = "2024-12-16",
                    Notes = "Giao hàng sau 17h.",
                    state = "Đang giao"
                )
            )
        )
    }

    // Lọc danh sách khi có sự thay đổi trong tìm kiếm
    val filteredCamelCase = dsOrder.value.filter {
        it.orderId.contains(searchText.text, ignoreCase = true) ||
                it.CustomerName.contains(searchText.text, ignoreCase = true) ||
                it.CustomerAddress.contains(searchText.text, ignoreCase = true)
        it.state.contains(searchText.text, ignoreCase = true)
    }
*/
    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(10.dp)
            ) {
                // Tìm kiếm
                TextField(
                    value = searchText,
                    onValueChange = {searchText = it},
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "search"
                        )
                    },
                    placeholder = { Text(text = "Tìm kiếm") },
                    modifier = Modifier.fillMaxWidth()
                        .border(1.dp, color = Color.Gray, RoundedCornerShape(10.dp)),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))
                // Danh sách Đơn hàng
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(listAllOrder) { index ->
                        OrderItem(index = index,
                            onClick = {
                                navController.navigate("Order_detail_screen/${index.id}")
                            }
                        )
                    }
                }
//                LazyColumn(
//                    modifier = Modifier
//                        .fillMaxSize()
//                ) {
//                    items(listAllOrder) { //order ->
//                        CardDeviceFeatured(
//                            order = it)
//                    }
//                }
            }
        }
    )
}


@Composable
fun CardDeviceFeatured(order: Order) {
    Card(
    ) {
        Text(text = order.idCustomer)
    }
}


@Composable
fun OrderItem(index: Order, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(1.dp, Color.Gray)
            .padding(16.dp)
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            // Id
            Text(
                text = "ID: ${index.id}",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )

            // Tên Khách hàng
            Text(
                text = "Tên KH: ${index.nameRecipient}",
                style = MaterialTheme.typography.bodySmall
            )

            // SDT
            Text(
                text = "SDT: ${index.phone}",
                style = MaterialTheme.typography.bodySmall,
            )
            // Địa chỉ
            Text(
                text = "Địa chỉ: ${index.address}",
                style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,// Số dòng tối đa hiển thị
                overflow = TextOverflow.Ellipsis // Hiển thị ... khi dài
            )

        }

        // Hiển thị trạng thái
//        Text(
//            modifier = Modifier.fillMaxWidth().weight(1f),
//            text = "${index.state}",
//            style = MaterialTheme.typography.bodySmall,
//            color = if (index.state == "Đang giao") Color(0xFF006400) else Color.Red,
//            textAlign = TextAlign.End,
//
//            )
    }
}