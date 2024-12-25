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
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import java.text.DecimalFormat

/** Giao diện màn hình Chi tiết Khách Hàng (CustomerDetailScreen)
 * -------------------------------------------
 * Người code: Văn Nam Cao
 * Ngày viết: 20/12/2024
 * Lần cập nhật cuối cùng: 23/12/2024
 * -------------------------------------------
 * Input:
 *
 * Output: Chứa các thành phần giao diện của màn hình thông tin
 *  của 1 Khách hàng, bao gồm ảnh, Mã khách hàng, tên khách hàng,...
 * ------------------------------------------------------------
 * Người cập nhật:
 * Ngày cập nhật:
 * ------------------------------------------------------------
 * Nội dung cập nhật:
 *
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerDetailScreen(Id: Int?, navController: NavController) {
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
        Text("Không tìm thấy khách hàng")
        return
    }


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


    // Lấy thông tin Khách hàng từ id (ví dụ: từ danh sách hoặc API)
    val customer = remember {
        dsCustomer.value.firstOrNull { it.id == Id }
    }

    if (customer == null) {
        Text("Khách hàng không tồn tại")
        return
    }

    // Khai báo các biến state mới cho Product
    var employeeId by remember { mutableStateOf(customer.CustomerId) }
    var user by remember { mutableStateOf(customer.User) }
    var password by remember { mutableStateOf(customer.Password) }
    var name by remember { mutableStateOf(customer.Name) }
    var cccd by remember { mutableStateOf(customer.CCCD) }
    var phone by remember { mutableStateOf(customer.Phone) }
    var address by remember { mutableStateOf(customer.Address) }
    var dateOfBirth by remember { mutableStateOf(customer.DateOfBirth) }
    var set by remember { mutableStateOf(customer.Set) }
    var position by remember { mutableStateOf(customer.taxCode) }
    var email by remember { mutableStateOf(customer.Email) }
    var registrationDate by remember { mutableStateOf(customer.RegistrationDate) }
    // Sử dụng remember để giữ trạng thái checkbox khi thay đổi
    var checkedState = remember { customer.state }

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
                                // Quay về màn hình danh sách Employee
                                navController.navigate(Screen.EmployeeScreen.route)
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
                            text = "Chi Tiết Khách Hàng",
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
            // Hiển thị chi tiết Customer
            LazyColumn(
                modifier = Modifier.padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                item {
                    // Hiển thị ảnh
                    Image(
                        painter = rememberImagePainter(customer.image),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth().size(130.dp),
                        alignment = Alignment.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // ID
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "ID: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(67.dp))
                        BasicTextField(
                            value = employeeId,
                            onValueChange = { newValue -> employeeId = newValue },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                            modifier = Modifier.fillMaxWidth()
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .padding(16.dp)

                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    // User
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "User: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(48.dp))
                        BasicTextField(
                            value = user,
                            onValueChange = { newValue -> user = newValue },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                            modifier = Modifier.fillMaxWidth()
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .padding(16.dp)

                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    // Password
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Password: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(10.dp))
                        BasicTextField(
                            value = password,
                            onValueChange = { newValue -> password = newValue },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                            modifier = Modifier.fillMaxWidth()
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .padding(16.dp)

                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    // Tên Nhân viên
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Tên: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(58.dp))
                        BasicTextField(
                            value = name,
                            onValueChange = { newValue -> name = newValue },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                            modifier = Modifier.fillMaxWidth()
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .padding(16.dp)

                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    // CCCD
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "CCCD: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(44.dp))
                        BasicTextField(
                            value = cccd,
                            onValueChange = { newValue -> cccd = newValue},
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
                        Spacer(modifier = Modifier.width(57.dp))
                        BasicTextField(
                            value = phone.toString(),
                            onValueChange = { newValue -> phone = newValue.toIntOrNull()?:0 },
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
                    // Ngày sinh
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Ngày sinh: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(13.dp))
                        BasicTextField(
                            value = dateOfBirth,
                            onValueChange = { newValue -> dateOfBirth = newValue },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                            modifier = Modifier.fillMaxWidth()
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .padding(16.dp)

                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    // Giới tính
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Giới tính: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(27.dp))
                        OutlinedButton(
                            onClick = { isDropdownExpanded = !isDropdownExpanded },
                        ) {
                            Text(text = set)
                        }

                        // Điều chỉnh để DropdownMenu có thể đè lên LazyColumn
                        DropdownMenu(
                            expanded = isDropdownExpanded,
                            onDismissRequest = { isDropdownExpanded = false },
                            modifier = Modifier
                                .fillMaxWidth()
                                .zIndex(1f)  // Đảm bảo DropdownMenu được vẽ lên trên các phần tử khác
                        ) {
                            listOf("Nam", "Nữ","Khác").forEach { status ->
                                DropdownMenuItem(
                                    text = { Text(status) },
                                    onClick = {
                                        set = status
                                        isDropdownExpanded = false
                                    }
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    // Mã số thuế
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Mã số thuế: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(5.dp))
                        BasicTextField(
                            value = position,
                            onValueChange = { newValue -> position = newValue },
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
                        Spacer(modifier = Modifier.width(48.dp))
                        BasicTextField(
                            value = email,
                            onValueChange = { newValue -> email = newValue },
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
                        Spacer(modifier = Modifier.width(25.dp))
                        BasicTextField(
                            value = registrationDate,
                            onValueChange = { newValue -> registrationDate = newValue },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                            modifier = Modifier.fillMaxWidth()
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .padding(16.dp)

                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    //Địa chỉ
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        //verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Địa chỉ: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(40.dp))
                        BasicTextField(
                            value = address,
                            onValueChange = { newValue -> address = newValue },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                            modifier = Modifier.fillMaxWidth()
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .padding(16.dp)
                                .size(100.dp)

                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    // Trạng thái checkbox
                    Row(
                    ) {
                        Text(
                            text = "Trạng thái: ",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                        if (checkedState) {
                            Checkbox(
                                checked = checkedState,
                                onCheckedChange = { isChecked ->
                                    checkedState = isChecked
                                    // Cập nhật trạng thái của customer nếu cần
                                    customer.state = isChecked
                                },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = Color(0xFF2E7D32), // Màu xanh lá đậm khi checkbox được check
                                    uncheckedColor = Color.Gray,     // Màu khi checkbox không được check
                                    checkmarkColor = Color.White     // Màu dấu check
                                )
                            )
                        }
                    }
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