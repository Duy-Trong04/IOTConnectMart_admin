package com.example.iotconnectmart_admin.screen.ImportProduct


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.iotconnectmart_admin.data.ImportProduct




/** Man hình Chi tiêts nhập hàng(ImportProductDetailScreen())
 * -------------------------------------------
 * Người code: Nguyễn Mạnh Cường
 * Ngày viết: 18/12/2024
 * Lần cập nhật cuối cùng: 18/12/2024
 * -------------------------------------------
 * Input: chưa có
 *
 * Output: Chứa các thành phần đối tượng màn hình
 * ------------------------------------------------------------
 * Người cập nhật:
 * Ngày cập nhật:
 * ------------------------------------------------------------
 * Nội dung cập nhật:
 *
 */
@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true)
@Composable
fun ImportProductDetailScreen(navController: NavHostController) {

    // Tạo các biến trạng thái cho từng mục chi tiết
    var id by remember { mutableStateOf("") }
    var creator by remember { mutableStateOf("") }
    var importDate by remember { mutableStateOf("") }
    var productName by remember { mutableStateOf("") }
    var verificationFile by remember { mutableStateOf("") }
    var importPrice by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var totalAmount by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("Đã nhận") }

    //Tao bien va danh sach dropdown
    var isDropdownExpanded by remember { mutableStateOf(false) }
    val statusOptions = listOf("Đang vận chuyển", "Đã nhận", "Đang xử lý", "Đã hủy", "Chờ xác nhận", "Chờ thanh toán", "Đã thanh toán")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chi tiết đơn nhập hàng") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF5F9EFF), // Màu nền
                    titleContentColor = Color.White // Màu văn bản tiêu đề
                ),
                navigationIcon = {
                    IconButton(onClick = { /* Xử lý sự kiện Back */ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item { DetailItem(label = "ID Đơn Nhập Kho", value = id, onValueChange = { id = it }) }
                item { DetailItem(label = "Người tạo", value = creator, onValueChange = { creator = it }) }
                item { DetailItem(label = "Ngày nhập hàng", value = importDate, onValueChange = { importDate = it }) }
                item { DetailItem(label = "Tên sản phẩm", value = productName, onValueChange = { productName = it }) }
                item { DetailItem(label = "File xác minh", value = verificationFile, onValueChange = { verificationFile = it }) }
                item { DetailItem(label = "Giá nhập", value = importPrice, onValueChange = { importPrice = it }) }
                item { DetailItem(label = "Số lượng", value = quantity, onValueChange = { quantity = it }) }
                item { DetailItem(label = "Thành tiền", value = totalAmount, onValueChange = { totalAmount = it }) }
                item { DetailItem(label = "Ghi chú", value = note, onValueChange = { note = it }) }
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(text = "Trạng thái", fontSize = 14.sp, color = Color.Gray)
                        ExposedDropdownMenuBox(
                            expanded = isDropdownExpanded,
                            onExpandedChange = { isDropdownExpanded = !isDropdownExpanded },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            OutlinedTextField(
                                readOnly = true,
                                value = status,
                                onValueChange = {},
                                label = { Text("Trạng thái") },
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDropdownExpanded) },
                                modifier = Modifier.menuAnchor()
                            )
                            ExposedDropdownMenu(
                                expanded = isDropdownExpanded,
                                onDismissRequest = { isDropdownExpanded = false }
                            ) {
                                statusOptions.forEach { option ->
                                    DropdownMenuItem(
                                        text = { Text(option) },
                                        onClick = {
                                            status = option
                                            isDropdownExpanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))

                    Box (
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ){
                        Button(
                            onClick = { /* Lưu thay đổi */ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5F9EFF)),
                            //modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Text("LƯU", color = Color.White)
                        }
                    }
                }
            }
        }
    )
}



/*
Tham so ham DetailItem()
* label: String: Chuỗi văn bản đại diện cho nhãn của mục chi tiết.
value: String: Giá trị văn bản hiển thị trong trường nhập liệu (TextField).
onValueChange: (String) -> Unit: Hàm callback được gọi khi giá trị của trường nhập liệu thay đổi
Ham khong tra ve gia tri
*/
@Composable
fun DetailItem(label: String, value: String, onValueChange: (String) -> Unit) {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
        //verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(text = label, fontSize = 14.sp, color = Color.Gray,modifier= Modifier.padding(8.dp).width(120.dp) )
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.padding(16.dp).fillMaxWidth()
        )
        Divider(modifier = Modifier.padding(vertical = 4.dp))
    }
}
