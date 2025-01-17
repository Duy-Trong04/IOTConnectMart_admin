package com.example.iotconnectmart_admin

import android.icu.text.SimpleDateFormat
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.iotconnectmart_admin.order.OrderStatus
import com.example.iotconnectmart_admin.order.OrderViewModel
import java.text.DecimalFormat
import java.util.Date
import java.util.Locale

/** Giao diện màn hình Chi tiết Đơn hàng (OrderDetailScreen)
 * -------------------------------------------
 * Người code: Văn Nam Cao
 * Ngày viết: 22/12/2024
 * Lần cập nhật cuối cùng: 23/12/2024
 * -------------------------------------------
 * Input: Id: Int, navController: NavController,viewModel: OrderViewModel
 *
 * Output: Chứa các thành phần giao diện của màn hình thông tin
 *  của 1 đơn hàng cụ thể trong danh sách đơn hàng
 * ------------------------------------------------------------
 * Người cập nhật:Nguyen Manh Cuong
 * Ngày cập nhật: 14/01/2025
 * ------------------------------------------------------------
 * Nội dung cập nhật:
 * Load duoc du lieu tu API
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDetailScreen(Id: Int, navController: NavController,viewModel: OrderViewModel) {

//    var order :Order by remember { mutableStateOf(
//        Order(0,"","","","","","","","","","","","","",1))
//    }
    viewModel.getOrderById(Id)
    LaunchedEffect(Id) {
        viewModel.getOrderById(Id)
    }
    viewModel.getOrderById(Id)
    var order = viewModel.order
    //val order by viewModel.order.observeAsState()
   // val orderState = remember { mutableStateOf<Order?>(null) }

//    val orderState = remember { mutableStateOf<Order?>(null) }
//    LaunchedEffect(Id) { viewModel.getOrderById(id) }

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


   //val dsOrder = viewModel.listAllOrder
//    val dsOrderCamelCase = remember {}
//
//
//    // Lấy thông tin Đơn hàng từ id (ví dụ: từ danh sách hoặc API)
//    val order = remember {
//        dsOrder.value.firstOrNull { it.id == Id }
//    }

    // Khai báo các biến state mới cho Product

    // Tính toán tổng số lượng và tổng tiền từ danh sách
//    var totalQuantity by remember {
//        mutableStateOf(dsOrderCamelCase.value.sumOf { it.quantity })
//    }
//    var totalAmount by remember {
//        mutableStateOf(dsOrderCamelCase.value.sumOf { it.quantity * it.sellingPrice })
//    }
    //val totalAmount = formatCurrency(order.totalAmount as Double)
    // Lấy số lượng sản phẩm khác nhau
    //var productCount = dsOrderCamelCase.value.size
    // Khai báo các biến state mới cho thông tin đơn hàng
    var orderID by remember { mutableStateOf(order.id ) }
    var nameRecipient by remember { mutableStateOf(order.nameRecipient) }
    var customerPhone by remember { mutableStateOf(order.phone ) }
    var customerAddress by remember { mutableStateOf(order.address ) }
    //var customerEmail by remember { mutableStateOf(order?.) }
    var emloyeeID by remember { mutableStateOf(order.idEmployee ) }
//   var orderDate by remember { mutableStateOf(order.OrderDate) }
    var orderNotes by remember { mutableStateOf(order?.note?:"" ) }
    // Sử dụng remember để giữ trạng thái checkbox khi thay đổi
    //var checkedState = remember { order.state }

    var isPlatformDropdownExpanded by remember { mutableStateOf(false) }
    var selectedPlatformOrder by remember { mutableStateOf(order?.platformOrder?:"") }
    var selectedPaymentMethod by remember { mutableStateOf(order?.paymentMethod?:"") }

    var isStatusDropdownExpanded by remember { mutableStateOf(false) }
    var selectedStatus by remember { mutableStateOf(OrderStatus.values().first { it.status == order.status}.displayName) }
    var selectedStatusValue by remember { mutableStateOf(OrderStatus.CHO_VAN_XAC_NHAN.status) }
    val listStatus = OrderStatus.values().map { it.displayName }

    // Giá trị mặc định ban đầu từ order
    var created_at by remember { mutableStateOf(order.created_at) }
    var updated_at by remember { mutableStateOf(order.updated_at) }
    var accept_at by remember { mutableStateOf(order.accept_at) }

    //lay ket qua tu viewmodel
    val resultUpdate = viewModel.orderUpdateResult
    var showAlertDialog by remember { mutableStateOf(false) }


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
                                navController.navigate(Screen.HomeScreen.route)
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
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = paddingValue),
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
                modifier = Modifier
                    .padding(paddingValues)
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
                            value = orderID.toString(),
                            // Kiểm tra và chuyển đổi giá trị mới thành Int
                            onValueChange = { newValue -> orderID = newValue.toIntOrNull() ?: orderID },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                            readOnly = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .padding(16.dp)

                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    /*  Row(
                          modifier = Modifier.fillMaxWidth(),
                          horizontalArrangement = Arrangement.SpaceBetween,
                          verticalAlignment = Alignment.CenterVertically
                      ) {
                          Text(text = "Email: ", fontWeight = FontWeight.Bold)
                          Spacer(modifier = Modifier.width(65.dp))
                          BasicTextField(
                              value = "",
                              onValueChange={},
                              //onValueChange = { newValue -> customerEmail = newValue },
                              textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                              modifier = Modifier.fillMaxWidth()
                                  .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                  .padding(16.dp)

                          )
                      }*/
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
                            value = nameRecipient,
                            onValueChange = { newValue -> nameRecipient = newValue },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                            modifier = Modifier
                                .fillMaxWidth()
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
                            value = customerPhone,
                            onValueChange = { newValue -> customerPhone = newValue },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                            modifier = Modifier
                                .fillMaxWidth()
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
                            modifier = Modifier
                                .fillMaxWidth()
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
                        Text(text = "Mã người lập: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(5.dp))
                        BasicTextField(
                            value = emloyeeID,
                            onValueChange = { newValue -> emloyeeID = newValue},
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                            readOnly = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .padding(16.dp)

                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Nguồn đơn hàng: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(27.dp))
                        OutlinedButton(
                            onClick = { isPlatformDropdownExpanded = !isPlatformDropdownExpanded }
                        ) {
                            Text(text = selectedPlatformOrder)
                        }

                        // Điều chỉnh để DropdownMenu có thể đè lên LazyColumn
                        DropdownMenu(
                            expanded = isPlatformDropdownExpanded,
                            onDismissRequest = { isPlatformDropdownExpanded = false },
                            modifier = Modifier
                                .fillMaxWidth()
                                .zIndex(1f) // Đảm bảo DropdownMenu được vẽ lên trên các phần tử khác
                        ) {
                            val platformOrderOptions = listOf("Website", "Mobile App", "In-store")
                            platformOrderOptions.forEach { platform ->
                                DropdownMenuItem(
                                    text = { Text(platform) },
                                    onClick = {
                                        selectedPlatformOrder = platform // Cập nhật nguồn đơn hàng được chọn
                                        //viewModel.order.value?.platformOrder = platform // Cập nhật giá trị trong order
                                        isPlatformDropdownExpanded = false
                                    }
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    // Ngày lập
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Ngày lập:", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(25.dp))
                        BasicTextField(
                            value = created_at,
                            onValueChange = { newValue -> created_at = newValue },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                            readOnly = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .padding(16.dp)

                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    // Ngày Update
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Ngày cập nhật:", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(15.dp))
                        BasicTextField(
                            value = updated_at,
                            onValueChange = { newValue -> updated_at = newValue },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                            readOnly = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .padding(16.dp)

                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    // Ngày Chấp nhận
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Ngày chấp nhận:", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(13.dp))
                        BasicTextField(
                            value = accept_at,
                            onValueChange = { newValue -> accept_at = newValue },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                            readOnly = true,
                            modifier = Modifier
                                .fillMaxWidth()
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
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .padding(16.dp)
                                .size(100.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    var isPaymentDropdownExpanded by remember { mutableStateOf(false) }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Phương thức thanh toán: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(27.dp))
                        OutlinedButton(
                            onClick = { isPaymentDropdownExpanded = !isPaymentDropdownExpanded }
                        ) {
                            Text(text = selectedPaymentMethod.toString())
                        }

                        // Điều chỉnh để DropdownMenu có thể đè lên LazyColumn
                        DropdownMenu(
                            expanded = isPaymentDropdownExpanded,
                            onDismissRequest = { isPaymentDropdownExpanded = false },
                            modifier = Modifier
                                .fillMaxWidth()
                                .zIndex(1f) // Đảm bảo DropdownMenu được vẽ lên trên các phần tử khác
                        ) {
                            val paymentMethods = listOf("Thanh toán khi nhận hàng (COD)", "Chuyển khoản ngân hàng", "Thanh toán qua thẻ")
                            paymentMethods.forEach { method ->
                                DropdownMenuItem(
                                    text = { Text(method) },
                                    onClick = {
                                        selectedPaymentMethod = method // Cập nhật phương thức thanh toán được chọn
                                        //viewModel.order.value?.paymentMethod = method // Cập nhật giá trị trong order
                                        isPaymentDropdownExpanded = false
                                    }
                                )
                            }
                        }
                    }
                    // paymentMethods hinh thuc thanh toan
//                    var isPaymentDropdownExpanded by remember { mutableStateOf(false) }
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.Start,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Text(text = "Phương thức thanh toán: ", fontWeight = FontWeight.Bold)
//                        Spacer(modifier = Modifier.width(27.dp))
//                        OutlinedButton(
//                            onClick = { isPaymentDropdownExpanded = !isPaymentDropdownExpanded }
//                        ) {
//                            Text(text = selectedPaymentMethod.toString())
//                        }
//
//                        // Điều chỉnh để DropdownMenu có thể đè lên LazyColumn
//                        DropdownMenu(
//                            expanded = isPaymentDropdownExpanded,
//                            onDismissRequest = { isPaymentDropdownExpanded = false },
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .zIndex(1f) // Đảm bảo DropdownMenu được vẽ lên trên các phần tử khác
//                        ) {
//                        }
//                        val paymentMethods = listOf("Thanh toán khi nhận hàng (COD)", "Chuyển khoản ngân hàng", "Thanh toán qua thẻ")
//                        paymentMethods.forEach { method ->
//                            DropdownMenuItem(
//                                text = { Text(method) },
//                                onClick = {
//                                    selectedPaymentMethod = method // Cập nhật phương thức thanh toán được chọn
//                                    //viewModel.order.value?.paymentMethod = method // Cập nhật giá trị trong order
//                                    isPaymentDropdownExpanded = false
//                                }
//                            )
//                        }
//                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    // Trang thái
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.Start,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Text(text = "Trạng thái: ", fontWeight = FontWeight.Bold)
//                        Spacer(modifier = Modifier.width(27.dp))
//                        OutlinedButton(
//                            onClick = { isDropdownExpanded = !isDropdownExpanded },
//                        ) {
//                            Text(text = "checkedState")
//                        }

                        // Điều chỉnh để DropdownMenu có thể đè lên LazyColumn
//                        DropdownMenu(
//                            expanded = isDropdownExpanded,
//                            onDismissRequest = { isDropdownExpanded = false },
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .zIndex(1f)  // Đảm bảo DropdownMenu được vẽ lên trên các phần tử khác
//                        ) {
//                            listOf("Đang giao", "Chưa giao").forEach { status ->
//                                DropdownMenuItem(
//                                   text = { Text(status) },
//                                    onClick = {
//                                     true = status
//                                      isDropdownExpanded = false
//                                   }
//                                )
//                            }
//                        }
                    // }
//                    Column(modifier = Modifier.fillMaxWidth()){
//                        dsOrderCamelCase.value.forEach {
//                            OrderItemCamelCase(index = it)
//                        }
//                    }
                    //}

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Trạng thái đơn hàng: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(27.dp))
                        OutlinedButton(
                            onClick = { isStatusDropdownExpanded = !isStatusDropdownExpanded }
                        ) {
                            Text(text = selectedStatus)
                        }

                        // Điều chỉnh để DropdownMenu có thể đè lên LazyColumn
                        DropdownMenu(
                            expanded = isStatusDropdownExpanded,
                            onDismissRequest = { isStatusDropdownExpanded = false },
                            modifier = Modifier
                                .fillMaxWidth()
                                .zIndex(1f) // Đảm bảo DropdownMenu được vẽ lên trên các phần tử khác
                        ) {
                           // val paymentMethods = listOf("Thanh toán khi nhận hàng (COD)", "Chuyển khoản ngân hàng", "Thanh toán qua thẻ")
                            listStatus.forEach { method ->
                                DropdownMenuItem(
                                    text = { Text(method) },
                                    onClick = {
                                       var selectedOrderStatus= OrderStatus.values().first{ it.displayName == method }
                                        selectedStatus = selectedOrderStatus.displayName// Cập nhật phương thức thanh toán được chọn
                                        //viewModel.order.value?.paymentMethod = method // Cập nhật giá trị trong order
                                       selectedStatusValue= selectedOrderStatus.status
                                        isStatusDropdownExpanded = false
                                    }
                                )
                            }
                        }
                    }
                    /*
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Trạng thái đơn hàng: ",
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(27.dp))
                        OutlinedButton(
                            onClick = { isStatusDropdownExpanded = !isStatusDropdownExpanded }
                        ) {
                            Text(text = selectedStatus ?: "Chưa chọn")
                        }
                        DropdownMenu(
                            expanded = isStatusDropdownExpanded,
                            onDismissRequest = { isStatusDropdownExpanded = false },
                            modifier = Modifier
                                .fillMaxWidth()
                                .zIndex(1f)
                        ) {
                            listStatus.forEach { status ->
                                DropdownMenu(
                                    expanded = isPaymentDropdownExpanded,
                                    onDismissRequest = { isPaymentDropdownExpanded = false },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .zIndex(1f) // Đảm bảo DropdownMenu được vẽ lên trên các phần tử khác
                                ) {
                                    Text(text = status)
                                }
                            }
                        }
                    }
*/
                    Spacer(modifier = Modifier.height(8.dp))
                    // Tổng sản phẩm
//                    Text(text = "Tổng sản phẩm: ", fontWeight = FontWeight.Bold)
//                    Text(text = "productCount")
//                    Spacer(modifier = Modifier.height(8.dp))
//                    // Tổng số lượng
//                    Text(text = "Tổng số lượng: ", fontWeight = FontWeight.Bold)
//                    Text(text = "totalQuantity")
//
//                    Spacer(modifier = Modifier.height(8.dp))
                    //Tổng giá
                    Text(text = "Tổng tiền: ", fontWeight = FontWeight.Bold)
                    //val formatter = DecimalFormat("#,###")

                    Text(text = order.totalAmount + " VND", fontWeight = FontWeight.Bold,color = Color.Red)
                    Spacer(modifier = Modifier.height(30.dp))
                    // Nút Lưu
                    Button(
                        onClick = {/* Handle update slideshow*/

                            var formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                            val currentDate = Date()
                            updated_at= formatter.format(currentDate)

                            var updateOrder = Order(
                                orderID,
                                order.idCustomer,
                                order.totalAmount,
                                selectedPaymentMethod,
                                customerAddress,
                                order.accountNumber,
                                customerPhone,
                                nameRecipient,
                                orderNotes ,
                                selectedPlatformOrder,
                                created_at,
                                updated_at,
                                accept_at,
                                order.idEmployee,
                                selectedStatusValue,)
                            viewModel.updateOrder(updateOrder)
                            viewModel.getAllOrder()
                            showAlertDialog = true
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF5D9EFF),
                            contentColor = Color.White
                        )
                    ) {
                        Text("Lưu")
                    }
                    if (showAlertDialog) {
                        AlertDialog(
                            onDismissRequest = {
                                navController.navigate(Screen.HomeScreen.route)
                            },
                            confirmButton = {
                                TextButton(
                                    onClick = {
                                        navController.navigate(Screen.HomeScreen.route)
                                    }) { Text("OK") } },
                            title = { Text("Thông báo") },
                            text = { Text(resultUpdate) }
                        )
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