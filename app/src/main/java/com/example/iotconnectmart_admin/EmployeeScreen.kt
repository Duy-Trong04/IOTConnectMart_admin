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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import coil.compose.rememberImagePainter

/** Giao diện màn hình Danh sách Nhân Viên (EmployeeScreen)
 * -------------------------------------------
 * Người code: Văn Nam Cao
 * Ngày viết: 21/12/2024
 * Lần cập nhật cuối cùng: 23/12/2024
 * -------------------------------------------
 * Input:
 *
 * Output: Chứa các thành phần giao diện của màn hình danh sách Nhân viên
 * ------------------------------------------------------------
 * Người cập nhật:
 * Ngày cập nhật:
 * ------------------------------------------------------------
 * Nội dung cập nhật:
 *
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeScreen(navController: NavController) {
    // Lấy context từ Composable
    val context = LocalContext.current
    // Kiểm tra thiết bị là tablet hay mobile
    val isTablet = isTablet(context)
    // Đặt giá trị padding
    val paddingValue = if (isTablet) 270.dp else 50.dp
    val withValue= if (isTablet) 300.dp else 200.dp
    val weightValue=if (isTablet) 200.dp else 0.dp

    // Tạo trạng thái cho TextField
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    // Tạo trạng thái cho Dropdown
    var isDropdownExpanded by remember { mutableStateOf(false) }
    var selectedStatus by remember { mutableStateOf("Hoat động") }

    val statusOptions = listOf("Tất cả", "Đang hoạt động", "Không hoạt động")

    val dsEmployee = remember {
        mutableStateOf(
            listOf(
                Employee(
                    id = 1,
                    employeeId = "EMP001",
                    User = "admin01",
                    Password = "password123",
                    Name = "Nguyen Thi Mai",
                    CCCD = "123456789012",
                    Phone = 123456789,
                    Address = "123 Hoang Hoa Tham, Hanoi",
                    DateOfBirth = "1990-05-01",
                    Set = "Nữ",
                    Position = "Quản lý",
                    Email = "mainguyen@example.com",
                    RegistrationDate = "2024-01-01",
                    state = true,
                    image = "https://via.placeholder.com/150"
                ),
                Employee(
                    id = 2,
                    employeeId = "EMP002",
                    User = "user02",
                    Password = "password456",
                    Name = "Tran Minh Tu",
                    CCCD = "987654321098",
                    Phone = 987654321,
                    Address = "456 Le Duan, Hanoi",
                    DateOfBirth = "1985-08-15",
                    Set = "Nam",
                    Position = "Admin",
                    Email = "tutransales@example.com",
                    RegistrationDate = "2024-02-15",
                    state = true,
                    image = "https://via.placeholder.com/150"
                ),
                Employee(
                    id = 3,
                    employeeId = "EMP003",
                    User = "employee03",
                    Password = "password789",
                    Name = "Le Thi Lan",
                    CCCD = "112233445566",
                    Phone = 112233445,
                    Address = "789 Ba Trieu, Hanoi",
                    DateOfBirth = "1995-11-20",
                    Set = "Nữ",
                    Position = "Nhân viên",
                    Email = "lanlehr@example.com",
                    RegistrationDate = "2024-03-05",
                    state = false,
                    image = "https://via.placeholder.com/150"
                ),
                Employee(
                    id = 4,
                    employeeId = "EMP004",
                    User = "staff04",
                    Password = "password101",
                    Name = "Pham Minh Tu",
                    CCCD = "223344556677",
                    Phone = 223344556,
                    Address = "101 Nguyen Trai, Hanoi",
                    DateOfBirth = "1988-02-10",
                    Set = "Nam",
                    Position = "Quản lý",
                    Email = "tuphamsales@example.com",
                    RegistrationDate = "2024-04-20",
                    state = true,
                    image = "https://via.placeholder.com/150"
                )
            )
        )
    }

    // Lọc danh sách khi có sự thay đổi trong tìm kiếm
    val filteredCamelCase = dsEmployee.value.filter {
        it.employeeId.contains(searchText.text, ignoreCase = true) ||
                it.User.contains(searchText.text, ignoreCase = true) ||
                it.Name.contains(searchText.text, ignoreCase = true)
        it.CCCD.contains(searchText.text, ignoreCase = true) ||
                it.Address.contains(searchText.text, ignoreCase = true)
        it.Position.contains(searchText.text, ignoreCase = true)
    }


    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding()
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

                // Dropdown và Nút "Thêm Nhân viên"
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Nút "Thêm Nhân viên"
                    Button(
                        onClick = { navController.navigate(Screen.AddEmployeeScreen.route) },
                        modifier = Modifier.width(withValue).size(64.dp).padding(top=9.dp, start = 6.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF5D9EFF),
                            contentColor = Color.White
                        ), shape = RectangleShape
                    ) {
                        Text("THÊM SẢN PHẨM")
                    }

                    Spacer(modifier = Modifier.width(weightValue))

                    // Dropdown trạng thái

                    ExposedDropdownMenuBox(
                        expanded = isDropdownExpanded,
                        onExpandedChange = { isDropdownExpanded = !isDropdownExpanded },
                        modifier = Modifier
                            .weight(1f),
                    ) {
                        OutlinedTextField(
                            value = selectedStatus,
                            onValueChange = { },
                            readOnly = true,
                            label = { Text("Trạng thái") },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDropdownExpanded) },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth()
                                .padding(end = 6.dp)
                        )
                        ExposedDropdownMenu(
                            expanded = isDropdownExpanded,
                            onDismissRequest = { isDropdownExpanded = false }
                        ) {
                            statusOptions.forEach { status ->
                                DropdownMenuItem(
                                    text = { Text(status) },
                                    onClick = {
                                        selectedStatus = status
                                        isDropdownExpanded = false
                                    }
                                )
                            }
                        }
                    }

                }
                // Danh sách Nhân viên
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(filteredCamelCase.size) { index ->
                        val index = filteredCamelCase[index]
                        CamelCaseItem(index = index,
                            onClick = {
                                navController.navigate("employee_detail_screen/${index.id}")
                            })
                    }
                }
            }
        }
    )
}

@Composable
fun CamelCaseItem(index: Employee, onClick: () -> Unit) {
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
                text = "ID: ${index.employeeId}",
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
                maxLines = 1,// Số dòng tối đa hiển thị
                overflow = TextOverflow.Ellipsis // Hiển thị ... khi dài
            )

            // Chức vụ
            Text(
                text = "Chức vụ: ${index.Position}",
                style = MaterialTheme.typography.bodySmall
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
                    onCheckedChange = {
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

