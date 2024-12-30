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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import java.text.DecimalFormat

/** Giao diện màn hình Thêm Sản Phẩm (AddCamelCaseScreen)
 * -------------------------------------------
 * Người code: Văn Nam Cao
 * Ngày viết: 19/12/2024
 * Lần cập nhật cuối cùng: 23/12/2024
 * -------------------------------------------
 * Input:
 *
 * Output: Chứa các thành phần giao diện của màn hình thông tin
 *  của 1 Sản Phẩm, bao gồm tên, danh mục, giá vốn, giá bán,
 * ------------------------------------------------------------
 * Người cập nhật:
 * Ngày cập nhật:
 * ------------------------------------------------------------
 * Nội dung cập nhật:
 *
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCamelCaseScreen(navController: NavController) {
    // Lấy context từ Composable
    val context = LocalContext.current
    // Kiểm tra thiết bị là tablet hay mobile
    val isTablet = isTablet(context)
    // Đặt giá trị padding
    val paddingValue = if (isTablet) 280.dp else 70.dp
    // Tạo trạng thái cho Dropdown
    var isDropdownExpanded by remember { mutableStateOf(false) }

    // Khai báo các biến state mới cho Product
    var productName: String by remember { mutableStateOf("") }
    var productCategory: String by remember { mutableStateOf("") }
    var costPrice: Double by remember { mutableStateOf(0.0) }
    var sellingPrice: Double by remember { mutableStateOf(0.0) }
    var soldQuantity: Int by remember { mutableStateOf(0) }
    var stockQuantity: Int by remember { mutableStateOf(0) }
    var productDescription: String by remember { mutableStateOf("") }
    var technicalSpecifications: String by remember { mutableStateOf("") }
    var state: String by remember { mutableStateOf("") }


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
                                // Quay về màn hình danh sách CamelCase
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
                            text = "Thêm Sản Phẩm",
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
            // Hiển thị chi tiết CamelCase
            LazyColumn(
                modifier = Modifier.padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                item {
                    // Hiển thị ảnh
                    Image(
                        painter = rememberImagePainter("https://via.placeholder.com/150"),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth().size(130.dp),
                        alignment = Alignment.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Tên sản phẩm
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Tên sản phẩm: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(1.dp))
                        BasicTextField(
                            value = productName,
                            onValueChange = { newValue -> productName = newValue },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                            modifier = Modifier.fillMaxWidth()
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .padding(16.dp)

                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    // danh mục
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Danh mục: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(33.dp))
                        BasicTextField(
                            value = productCategory,
                            onValueChange = { newValue -> productCategory = newValue },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                            modifier = Modifier.fillMaxWidth()
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .padding(16.dp)

                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    // Giá vốn
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Giá vốn: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(51.dp))
                        BasicTextField(
                            value = if (costPrice == 0.0) "" else costPrice.toString(),
                            onValueChange = { newValue -> costPrice = newValue.toDoubleOrNull()?:0.0 },
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
                    // Giá bán
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Giá bán: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(51.dp))
                        BasicTextField(
                            value = if (sellingPrice == 0.0) "" else sellingPrice.toString(),
                            onValueChange = { newValue -> sellingPrice = newValue.toDoubleOrNull()?:0.0 },
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
                    // Số lượng bán
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Số lượng bán: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(9.dp))
                        BasicTextField(
                            value = if (soldQuantity == 0) "" else soldQuantity.toString(),
                            onValueChange = { newValue -> soldQuantity = newValue.toIntOrNull()?:0 },
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
                    // Số lượng kho
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(text = "Số lượng kho: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(9.dp))
                        BasicTextField(
                            value = if (stockQuantity == 0) "" else stockQuantity.toString(),
                            onValueChange = { newValue -> stockQuantity = newValue.toIntOrNull()?:0 },
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
                    // Mô tả
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        //verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Mô tả: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(65.dp))
                        BasicTextField(
                            value = productDescription,
                            onValueChange = { newValue -> productDescription = newValue },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                            modifier = Modifier.fillMaxWidth()
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .padding(16.dp)
                                .size(100.dp)

                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    // Thông số kỹ thuật
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        //verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Thông số\nkỹ thuật: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(47.dp))
                        BasicTextField(
                            value = technicalSpecifications,
                            onValueChange = { newValue -> technicalSpecifications = newValue },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                            modifier = Modifier.fillMaxWidth()
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .padding(16.dp)
                                .size(100.dp)

                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    // Trạng thái
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Trạng thái: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(30.dp))
                        OutlinedButton(
                            onClick = { isDropdownExpanded = !isDropdownExpanded },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = state)
                        }

                        // Điều chỉnh để DropdownMenu có thể đè lên LazyColumn
                        DropdownMenu(
                            expanded = isDropdownExpanded,
                            onDismissRequest = { isDropdownExpanded = false },
                            modifier = Modifier
                                .fillMaxWidth()
                                .zIndex(1f)  // Đảm bảo DropdownMenu được vẽ lên trên các phần tử khác
                        ) {
                            listOf("Hoạt động", "Không hoạt động").forEach { status ->
                                DropdownMenuItem(
                                    text = { Text(status) },
                                    onClick = {
                                        state = status
                                        isDropdownExpanded = false
                                    }
                                )
                            }
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