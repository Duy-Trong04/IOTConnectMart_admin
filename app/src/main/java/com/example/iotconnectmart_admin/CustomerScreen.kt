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
import androidx.navigation.NavController
import coil.compose.rememberImagePainter

/** Giao diện màn hình Danh sách Khách Hàng (CustomerScreen)
 * -------------------------------------------
 * Người code: Văn Nam Cao
 * Ngày viết: 19/12/2024
 * Lần cập nhật cuối cùng: 23/12/2024
 * -------------------------------------------
 * Input:
 *
 * Output: Chứa các thành phần giao diện của màn hình danh sách Khách hàng
 * ------------------------------------------------------------
 * Người cập nhật:
 * Ngày cập nhật:
 * ------------------------------------------------------------
 * Nội dung cập nhật:
 *
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerScreen(navController: NavController) {
    // Lấy context từ Composable
    val context = LocalContext.current
    // Kiểm tra thiết bị là tablet hay mobile
    val isTablet = isTablet(context)
    // Đặt giá trị padding
    val paddingValue = if (isTablet) 270.dp else 50.dp

    // Tạo trạng thái cho TextField
    var searchText by remember { mutableStateOf(TextFieldValue("")) }

    val dsCustomer = remember {
        mutableStateOf(
            listOf(
                Customer(
                    id = 1,
                    CustomerId = "CUS001",
                    User = "admin01",
                    Password = "password123",
                    Name = "Nguyen Thi Mai",
                    CCCD = "123456789012",
                    Phone = 123456789,
                    Address = "123 Hoang Hoa Tham, Hanoi",
                    DateOfBirth = "1990-05-01",
                    Set = "Nữ",
                    taxCode = "45646489",
                    Email = "mainguyen@example.com",
                    RegistrationDate = "2024-01-01",
                    state = true,
                    image = "https://via.placeholder.com/150"
                ),
                Customer(
                    id = 2,
                    CustomerId = "CUS002",
                    User = "user02",
                    Password = "password456",
                    Name = "Tran Minh Tu",
                    CCCD = "987654321098",
                    Phone = 987654321,
                    Address = "456 Le Duan, Hanoi",
                    DateOfBirth = "1985-08-15",
                    Set = "Nam",
                    taxCode = "56456476456",
                    Email = "tutransales@example.com",
                    RegistrationDate = "2024-02-15",
                    state = true,
                    image = "https://via.placeholder.com/150"
                ),
                Customer(
                    id = 3,
                    CustomerId = "CUS003",
                    User = "employee03",
                    Password = "password789",
                    Name = "Le Thi Lan",
                    CCCD = "112233445566",
                    Phone = 112233445,
                    Address = "789 Ba Trieu, Hanoi",
                    DateOfBirth = "1995-11-20",
                    Set = "Nữ",
                    taxCode = "457468947",
                    Email = "lanlehr@example.com",
                    RegistrationDate = "2024-03-05",
                    state = false,
                    image = "https://via.placeholder.com/150"
                ),
                Customer(
                    id = 4,
                    CustomerId = "CUS004",
                    User = "staff04",
                    Password = "password101",
                    Name = "Pham Minh Tu",
                    CCCD = "223344556677",
                    Phone = 223344556,
                    Address = "101 Nguyen Trai, Hanoi",
                    DateOfBirth = "1988-02-10",
                    Set = "Nam",
                    taxCode = "1214856",
                    Email = "tuphamsales@example.com",
                    RegistrationDate = "2024-04-20",
                    state = true,
                    image = "https://via.placeholder.com/150"
                )
            )
        )
    }

    // Lọc danh sách khi có sự thay đổi trong tìm kiếm
    val filteredCamelCase = dsCustomer.value.filter {
        it.CustomerId.contains(searchText.text, ignoreCase = true) ||
                it.User.contains(searchText.text, ignoreCase = true) ||
                it.Name.contains(searchText.text, ignoreCase = true)
        it.CCCD.contains(searchText.text, ignoreCase = true) ||
                it.Address.contains(searchText.text, ignoreCase = true)
        it.taxCode.contains(searchText.text, ignoreCase = true)
    }

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
                                // Quay về màn hình Trang chủ
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
                            text = "Danh Sách Khách Hàng",
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                // Tìm kiếm
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .border(1.dp, Color.Gray, RoundedCornerShape(20.dp)) // Bo góc cho border
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Trường nhập liệu tìm kiếm
                    Box(
                        modifier = Modifier.weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .padding(8.dp)
                    ) {
                        BasicTextField(
                            value = searchText,
                            onValueChange = { searchText = it },
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = MaterialTheme.typography.bodyMedium,
                            singleLine = true,
                            maxLines = 1,
                            decorationBox = { innerTextField -> innerTextField() },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Search
                            )
                        )
                    }

                    // Icon tìm kiếm ở cuối
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search Icon",
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(24.dp),
                        tint = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Dropdown và Nút "Thêm Khách Hàng"
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Nút "Thêm Khách Hàng"
                    Button(
                        onClick = { /* Handle add  */ },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("THÊM KHÁCH HÀNG")
                    }

                }
                // Danh sách Danh mục
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(filteredCamelCase.size) { index ->
                        val index = filteredCamelCase[index]
                        CustomerItem(index =index,
                            onClick = {
                                navController.navigate("customer_detail_screen/${index.id}")
                            })
                    }
                }
            }
        }
    )
}
@Composable
fun CustomerItem(index: Customer,onClick: () -> Unit) {
    // Sử dụng remember để giữ trạng thái checkbox khi thay đổi
    var checkedState = remember { index.state }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(1.dp, Color.Gray)
            .padding(16.dp)
            .clickable(onClick = onClick)
    ) {
        // Hiển thị hình ảnh
        Image(
            painter = rememberImagePainter(index.image),
            contentDescription = null,
            modifier = Modifier.size(60.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            // ID Nhân viên
            Text(
                text = "ID: ${index.CustomerId}",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )

            // Tên Nhân viên
            Text(
                text = "Tên: ${index.Name}",
                style = MaterialTheme.typography.bodySmall
            )

            // SDT
            Text(
                text = "SDT: ${index.Phone}",
                style = MaterialTheme.typography.bodySmall,
            )

            // Địa chỉ
            Text(
                text = "Địa chỉ: ${index.Address}",
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,// Số dòng tối đa hiển thị
                overflow = TextOverflow.Ellipsis // Hiển thị ... khi dài
            )

            // Trạng thái checkbox
            Row(
            ) {
                Text(
                    text = "Trạng thái: ",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Checkbox(
                    checked = checkedState,
                    onCheckedChange = { isChecked ->
                        checkedState = isChecked
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF2E7D32), // Màu xanh lá đậm khi checkbox được check
                        uncheckedColor = Color.Gray,     // Màu khi checkbox không được check
                        checkmarkColor = Color.White     // Màu dấu check
                    )
                )
            }
        }
    }
}