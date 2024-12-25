package com.example.iotconnectmart_admin

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import java.text.DecimalFormat

/** Giao diện màn hình Chi tiết Đơn hàng (OrderDetailScreen)
 * -------------------------------------------
 * Người code: Văn Nam Cao
 * Ngày viết: 22/12/2024
 * Lần cập nhật cuối cùng: 23/12/2024
 * -------------------------------------------
 * Input:
 *
 * Output: Chứa các thành phần giao diện của màn hình thông tin
 *  của 1 đơn hàng cụ thể trong danh sách đơn hàng
 * ------------------------------------------------------------
 * Người cập nhật:
 * Ngày cập nhật:
 * ------------------------------------------------------------
 * Nội dung cập nhật:
 *
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDetailScreen(Id: Int?, navController: NavController) {
    // Tạo trạng thái cho Dropdown
    var isDropdownExpanded by remember { mutableStateOf(false) }
    // Lấy context từ Composable
    val context = LocalContext.current
    // Kiểm tra thiết bị là tablet hay mobile
    val isTablet = isTablet(context)
    // Đặt giá trị padding
    val paddingValue = if (isTablet) 270.dp else 50.dp
    // Kiểm tra id có hợp lệ hay không
    if (Id == null) {
        Text("Không tìm thấy đơn hàng")
        return
    }


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
    val dsOrderCamelCase = remember {
        mutableStateOf(
            listOf(
                OrderCamelCase(
                    id = 1,
                    productId = 3,
                    productName = "Security Camera",
                    sellingPrice = 220000.0,
                    quantity = 2,
                    image = "https://via.placeholder.com/150"
                ),
                OrderCamelCase(
                    id = 2,
                    productId = 4,
                    productName = "Smart Door Lock",
                    sellingPrice = 300000.0,
                    quantity = 1,
                    image = "https://via.placeholder.com/150"
                )
            )
        )

    }


    // Lấy thông tin Đơn hàng từ id (ví dụ: từ danh sách hoặc API)
    val order = remember {
        dsOrder.value.firstOrNull { it.id == Id }
    }

    if (order == null) {
        Text("Đơn hàng không tồn tại")
        return
    }

    // Khai báo các biến state mới cho Product

    // Tính toán tổng số lượng và tổng tiền từ danh sách
    var totalQuantity by remember {
        mutableStateOf(dsOrderCamelCase.value.sumOf { it.quantity })
    }
    var totalAmount by remember {
        mutableStateOf(dsOrderCamelCase.value.sumOf { it.quantity * it.sellingPrice })
    }
    val formattedAmount = formatCurrency(totalAmount)
    // Lấy số lượng sản phẩm khác nhau
    var productCount = dsOrderCamelCase.value.size

    var orderID by remember { mutableStateOf(order.orderId) }
    var customerName by remember { mutableStateOf(order.CustomerName) }
    var customerPhone by remember { mutableStateOf(order.CustomerPhone) }
    var customerAddress by remember { mutableStateOf(order.CustomerAddress) }
    var customerEmail by remember { mutableStateOf(order.CustomerEmail) }
    var emloyeeName by remember { mutableStateOf(order.EmployeeName) }
    var orderDate by remember { mutableStateOf(order.OrderDate) }
    var orderNotes by remember { mutableStateOf(order.Notes) }
    // Sử dụng remember để giữ trạng thái checkbox khi thay đổi
    var checkedState = remember { order.state }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically // Căn chỉnh theo chiều dọc
                    ) {
                        IconButton(
                            onClick = {
                                // Quay về màn hình danh sách Order
                                navController.navigate(Screen.OrderScreen.route)
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Quay về",
                                tint = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f)) // Để tiêu đề chiếm phần còn lại

                        Text(
                            text = "Chi Tiết Đơn Hàng",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth().padding(start = paddingValue),
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF5D9EFF),
                    titleContentColor = Color.White
                ),
            )
        },
        content = { paddingValues ->
            // Hiển thị chi tiết Order
            LazyColumn(
                modifier = Modifier.padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                item {
                    // ID
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "ID: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(90.dp))
                        BasicTextField(
                            value = orderID,
                            onValueChange = { newValue -> orderID = newValue },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                            modifier = Modifier.fillMaxWidth()
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .padding(16.dp)

                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    // Email
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Email: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(65.dp))
                        BasicTextField(
                            value = customerEmail,
                            onValueChange = { newValue -> customerEmail = newValue },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                            modifier = Modifier.fillMaxWidth()
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .padding(16.dp)

                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    // họ tên
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Họ tên: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(55.dp))
                        BasicTextField(
                            value = customerName,
                            onValueChange = { newValue -> customerName = newValue },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                            modifier = Modifier.fillMaxWidth()
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .padding(16.dp)

                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    // SDT
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "SDT: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(75.dp))
                        BasicTextField(
                            value = customerPhone.toString(),
                            onValueChange = { newValue -> customerPhone = newValue.toIntOrNull()?:0 },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                            modifier = Modifier.fillMaxWidth()
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .padding(16.dp),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number // Chỉ cho phép bàn phím số
                            )

                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    // Địa chỉ
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Địa chỉ: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(55.dp))
                        BasicTextField(
                            value = customerAddress,
                            onValueChange = { newValue -> customerAddress = newValue },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                            modifier = Modifier.fillMaxWidth()
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .padding(16.dp)

                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    // Nhân viên lập
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Nhân viên lập: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(5.dp))
                        BasicTextField(
                            value = emloyeeName,
                            onValueChange = { newValue -> emloyeeName = newValue},
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                            modifier = Modifier.fillMaxWidth()
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .padding(16.dp)

                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    // Ngày lập
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Ngày lập: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(45.dp))
                        BasicTextField(
                            value = orderDate,
                            onValueChange = { newValue -> orderDate = newValue },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                            modifier = Modifier.fillMaxWidth()
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .padding(16.dp)

                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    // Ghi chú
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        //verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Ghi chú: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(52.dp))
                        BasicTextField(
                            value = orderNotes,
                            onValueChange = { newValue -> orderNotes = newValue },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                            modifier = Modifier.fillMaxWidth()
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .padding(16.dp)
                                .size(100.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    // Trang thái
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Trạng thái: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(27.dp))
                        OutlinedButton(
                            onClick = { isDropdownExpanded = !isDropdownExpanded },
                        ) {
                            Text(text = checkedState)
                        }

                        // Điều chỉnh để DropdownMenu có thể đè lên LazyColumn
                        DropdownMenu(
                            expanded = isDropdownExpanded,
                            onDismissRequest = { isDropdownExpanded = false },
                            modifier = Modifier
                                .fillMaxWidth()
                                .zIndex(1f)  // Đảm bảo DropdownMenu được vẽ lên trên các phần tử khác
                        ) {
                            listOf("Đang giao", "Chưa giao").forEach { status ->
                                DropdownMenuItem(
                                    text = { Text(status) },
                                    onClick = {
                                        checkedState = status
                                        isDropdownExpanded = false
                                    }
                                )
                            }
                        }
                    }
                    Column(modifier = Modifier.fillMaxWidth()){
                        dsOrderCamelCase.value.forEach {
                            OrderItemCamelCase(index = it)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    // Tổng sản phẩm
                    Text(text = "Tổng sản phẩm: ", fontWeight = FontWeight.Bold)
                    Text(text = "$productCount")
                    Spacer(modifier = Modifier.height(8.dp))
                    // Tổng số lượng
                    Text(text = "Tổng số lượng: ", fontWeight = FontWeight.Bold)
                    Text(text = "$totalQuantity")

                    Spacer(modifier = Modifier.height(8.dp))
                    //Tổng giá
                    Text(text = "Tổng tiền: ", fontWeight = FontWeight.Bold)
                    Text(text = "$formattedAmount", fontWeight = FontWeight.Bold,color = Color.Red)
                    Spacer(modifier = Modifier.height(30.dp))
                    // Nút Lưu
                    Button(
                        onClick = { /* Handle update slideshow */ },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF5D9EFF),
                            contentColor = Color.White
                        )
                    ) {
                        Text("Lưu")
                    }
                }
            }
        }
    )
}

@Composable
fun OrderItemCamelCase(index: OrderCamelCase) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(1.dp, Color.Gray)
            .padding(16.dp)
    ) {
        //Hình ảnh
        Image(
            painter = rememberImagePainter(data = index.image),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {

            // Tên Sản phẩm
            Text(
                text = "Tên KH: ${index.productName}",
                style = MaterialTheme.typography.bodySmall
            )

            //số lượng
            Text(
                text = "Số lượng: ${index.quantity}",
                style = MaterialTheme.typography.bodySmall,
            )
            // Đơn giá
            Text(
                text = "Giá: ${index.sellingPrice}",
                style = MaterialTheme.typography.bodySmall
            )

        }

    }
}
fun formatCurrency(amount: Double): String {
    val formatter = DecimalFormat("#,###")
    return formatter.format(amount) + " VND"
}