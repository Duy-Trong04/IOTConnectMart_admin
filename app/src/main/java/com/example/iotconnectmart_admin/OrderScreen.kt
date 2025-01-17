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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.times
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.iotconnectmart_admin.customer.GenderStatus
import com.example.iotconnectmart_admin.order.OrderStatus
import com.example.iotconnectmart_admin.order.OrderViewModel
import com.example.iotconnectmart_admin.order.statusOptions

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
fun OrderScreen(navController: NavController) {

    var orderViewModel:OrderViewModel = viewModel()

    val listAllOrder by orderViewModel.listAllOrder.collectAsState()
    LaunchedEffect(Unit){
        orderViewModel.getAllOrder()
    }

    // Lấy context từ Composable
    val context = LocalContext.current
    // Kiểm tra thiết bị là tablet hay mobile
    val isTablet = isTablet(context)
    // Đặt giá trị padding
    val paddingValue = if (isTablet) 270.dp else 50.dp

    // Tạo trạng thái cho TextField
    var searchText by remember { mutableStateOf(TextFieldValue("")) }

    var isDropdownExpanded by remember { mutableStateOf(false) }
    var selectedStatus by remember { mutableStateOf("Tất cả") }
    var selectedStatusValue by remember { mutableStateOf(statusOptions.CHO_VAN_XAC_NHAN.status) }
    //val statusOptions = listOf( "Tất cả", "Đang vận chuyển", "Đã nhận", "Đang xử lý", "Đã hủy", "Chờ xác nhận", "Chờ thanh toán", "Đã thanh toán" )
    val listOptions = statusOptions.values().map { it.displayName }
    val filteredOrder = if (selectedStatus == "Tất cả"&& searchText.text=="") {
        listAllOrder
    } else {
        listAllOrder.filter {
            (selectedStatus == "Tất cả" || it.status == selectedStatusValue) &&
             (it.phone.contains(searchText.text, ignoreCase = true) ||
             it.nameRecipient.contains(searchText.text, ignoreCase = true))
        }
    }

    //Lay kich thuoc man hinh
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    var seach= listAllOrder.filter {
        it.phone.contains(searchText.text, ignoreCase = true) ||
        it.nameRecipient.contains(searchText.text, ignoreCase = true)
    }


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
                        onValueChange = {
                            searchText = it
                        },
                        singleLine = true,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "search"
                            )
                        },
                        placeholder = { Text(text = "Tìm kiếm ${listAllOrder.size}") },
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
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        ExposedDropdownMenuBox(
                            expanded = isDropdownExpanded,
                            onExpandedChange = { isDropdownExpanded = !isDropdownExpanded },
                            modifier = Modifier.width((1/3f).times(screenWidth))
                        ) {
                            OutlinedTextField(
                                readOnly = true,
                                value = selectedStatus,
                                onValueChange = { },
                                label = { Text("Trạng thái") },
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDropdownExpanded) },
                                modifier = Modifier.menuAnchor()
                            )
                            ExposedDropdownMenu(
                                expanded = isDropdownExpanded,
                                onDismissRequest = { isDropdownExpanded = false }
                            ) {
                                listOptions.forEach { status ->
                                    DropdownMenuItem(
                                        text = { Text(status) },
                                        onClick = {
                                            var selectedGenderStatus= statusOptions.values().first{ it.displayName == status }
                                            selectedStatus = status
                                            selectedStatusValue= selectedGenderStatus.status
                                            isDropdownExpanded = false
                                        }
                                    )
                                }
                            }
                        }
//                        Box {
//                            Button(
//                                onClick = { /* Chức năng của nút */  navController.navigate(Screen.ImportProductDetail.route)},
//                                colors = ButtonDefaults.buttonColors(
//                                    containerColor = Color(0xFF5F9EFF)),
//                                shape = RectangleShape,
//                                modifier = Modifier.width((1/3f).times(screenWidth))
//                            ) { Text("Thêm", color = Color.White) }
//                        }

                    }
                    // Danh sách Đơn hàng
//                    LazyColumn(
//                        modifier = Modifier.fillMaxSize()
//                    ) {
//                        items(listAllOrder) {
//                            OrderItem(index =index,
//                                onClick = {
//                                    viewModel.getOrderById(index.id)
//                                    navController.navigate("Order_detail_screen/${index.id}")
//                                    viewModel.getOrderById(index.id)
//                                })
//                        }
//                    }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(listAllOrder) { //order ->
                        OrderItem(
                            order = it,
                            navController
                        )
                    }
                }
                }
            }
        )
    }
    @Composable
    fun OrderItem(order: Order, navController: NavController) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .border(1.dp, Color.Gray)
                .padding(16.dp)
                .clickable(onClick = {navController.navigate(Screen.OrderDetailScreen.route + "?id=${order.id}")} )
        ) {
            Column(modifier = Modifier.weight(1f)) {
                // Id
                Text(
                    text = "ID: ${order.id}",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium
                )

                // Tên Khách hàng
                Text(
                    text = "Tên KH: ${order.nameRecipient}",
                    style = MaterialTheme.typography.bodySmall
                )

                // SDT
                Text(
                    text = "SDT: ${order.phone}",
                    style = MaterialTheme.typography.bodySmall,
                )
                // Địa chỉ
                Text(
                    text = "Địa chỉ: ${order.address}",
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
