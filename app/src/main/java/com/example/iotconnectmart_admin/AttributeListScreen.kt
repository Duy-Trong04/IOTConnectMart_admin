package com.example.iotconnectmart_admin.screen.ManageAttributes.attributeList

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.iotconnectmart_admin.Screen
import com.example.iotconnectmart_admin.data.Attribute





/** Man hình  Chi Quản lý thuộc tính
 * -------------------------------------------
 * Người code: Nguyễn Mạnh Cường
 * Ngày viết: 18/12/2024
 * Lần cập nhật cuối cùng: 18/12/2024
 * -------------------------------------------
 * Input: chưa có
 *
 * Output: Chứa các thành phần đối tượng màn hình và danh sachs thuộc tính
 * ------------------------------------------------------------
 * Người cập nhật:
 * Ngày cập nhật:
 * ------------------------------------------------------------
 * Nội dung cập nhật:
 *
 */




/*
* Chua co tham so
* */
@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true)
@Composable
fun AttributeListScreen(navController:NavController) {

    var searchQuery by remember { mutableStateOf("") }

    var selectedStatus by remember { mutableStateOf("Tất cả") }
    var expanded by remember { mutableStateOf(false) }  // Khai báo biến expanded
    val statusOptions = listOf("Tất cả", "Đang hoạt động", "Không hoạt động")

    // Dữ liệu mẫu
    val attributes = listOf(
        Attribute("TT001", "Độ phân giải", "Hiển thị", "Tính năng kỹ thuật", "Text", true, "Đang hoạt động"),
        Attribute("TT002", "Công suất", "Hiệu năng", "Tính năng kỹ thuật", "Number", false, "Không hoạt động"),
        Attribute("TT003", "Dung lượng lưu trữ", "Bộ nhớ", "Thông số kỹ thuật", "Number", true, "Đang hoạt động"),
        Attribute("TT004", "Thời lượng pin", "Pin", "Thông số kỹ thuật", "Number", true, "Không hoạt động"),
        Attribute("TT005", "Hệ điều hành", "Phần mềm", "Thông tin hệ thống", "Text", false, "Đang hoạt động")
    )
    //trang thai loc
    val filteredProducts = if (selectedStatus == "Tất cả") {
        attributes
    } else {
        attributes.filter { it.status == selectedStatus }
    }
    // Trạng thái của từng checkbox
    var selectedStates by remember { mutableStateOf(List(attributes.size) { false }) }

    // Trạng thái checkbox "TẤT CẢ"
    val isAllChecked = selectedStates.all { it }

    //Lấy kích thước màn hình
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    //bien show lua chon
    var showDialog by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf<String?>(null) }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                //containerColor = Color(0xFF5F9EFF),
                //modifier = Modifier.height(96.dp),
                content = {
                    Row (
                        modifier = Modifier.fillMaxWidth().fillMaxSize().padding(8.dp),
                        //verticalArrangement = Arrangement.SpaceBetween,
                        //horizontalAlignment = Alignment.Start
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ){
                            Checkbox(
                                checked = !isAllChecked, // Kiểm tra tất cả thuộc tính
                                onCheckedChange = {isChecked ->
                                /* Xử lý khi kiểm tra tất cả */
                                selectedStates= List(attributes.size){isAllChecked}
                                }
                            )
                            Text("TẤT CẢ")
                        }
                        //Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            modifier = Modifier.fillMaxWidth().padding(8.dp),
                            onClick = { /* Xóa thuộc tính đã chọn */ },
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5F9EFF))
                        ) {
                            Text("XÓA THUỘC TÍNH", color = Color.White)
                        }
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    //.padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("Tìm kiếm") },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Tìm kiếm")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                // Dropdown trạng thái
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded },
                        modifier = Modifier.width((2/3f).times(screenWidth))
                    ) {
                        OutlinedTextField(
                            value = selectedStatus,
                            onValueChange = { },
                            readOnly = true,
                            label = { Text("Trạng thái") },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                            modifier = Modifier.weight(1f)
                                .menuAnchor()
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            statusOptions.forEach { status ->
                                DropdownMenuItem(
                                    text = { Text(status) },
                                    onClick = {
                                        selectedStatus = status
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {/* Xử lý thêm */showDialog= true },
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5F9EFF))
                    ) {
                        Text("THÊM", color = Color.White)
                    }

                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = { showDialog = false },
                            title = { Text("Chọn loại thêm") },
                            text = {
                                Column {
                                    Button(
                                        onClick = {
                                            selectedOption = "Thuộc tính"
                                            showDialog = false
                                            // Chuyển màn hình thêm chi tiết thuộc tính
                                            navController.navigate(Screen.AttributeDetailScreen.route)
                                        }
                                    ) {
                                        Text("Thêm chi tiết thuộc tính")
                                    }
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Button(
                                        onClick = {
                                            selectedOption = "Nhóm thuộc tính"
                                            showDialog = false
                                            // Chuyển màn hình thêm chi tiết nhóm thuộc tính
                                            navController.navigate(Screen.AttributeGroupScreen.route)
                                        }
                                    ) {
                                        Text("Thêm chi tiết nhóm thuộc tính")
                                    }
                                }
                            },
                            confirmButton = {
//                                TextButton(
//                                    onClick = {
//                                        // Xử lý khi người dùng xác nhận
//                                        showDialog = false
//                                    }
//                                ) {
//                                    Text("Xác nhận")
//                                }
                            },
                            dismissButton = {
                                TextButton(
                                    onClick = {
                                        // Xử lý khi người dùng hủy
                                        showDialog = false
                                    }
                                ) {
                                    Text("Hủy")
                                }
                            }
                        )
                    }
                }

                //Spacer(modifier = Modifier.height(16.dp))

                // Danh sách thuộc tính
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(filteredProducts) { filteredProducts ->
                        AttributeItem(filteredProducts)
                    }
                }
            }
        }
    )
}

@Composable
fun AttributeItem(
    attribute: Attribute
) {
    var checked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Checkbox(
                checked = checked,
                onCheckedChange = { checked = it },
            )
            Text("Trạng thái: ${if (attribute.status=="Đang hoạt động") "Đang hoạt động" else "Không hoạt động"}")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
           Row (
               modifier = Modifier.fillMaxWidth(),
               verticalAlignment = Alignment.CenterVertically,
               horizontalArrangement = Arrangement.Absolute.SpaceBetween
           ){
               Text("ID: ${attribute.id}", fontWeight = FontWeight.Bold)
               Text("Tên: ${attribute.name}")
           }
            Spacer(modifier = Modifier.height(3.dp))
            Text("Nhóm: ${attribute.group}")
            Text("Danh mục: ${attribute.category}")
        }
    }
}
