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

/** Giao diện màn hình Danh sách Sản Phẩm (CamelCaseScreen)
 * -------------------------------------------
 * Người code: Văn Nam Cao
 * Ngày viết: 19/12/2024
 * Lần cập nhật cuối cùng: 23/12/2024
 * -------------------------------------------
 * Input:
 *
 * Output: Chứa các thành phần giao diện của màn hình Sản Phẩm, bao gồm
 *  Tên sản phẩm, thông số kỹ thuật, trạng thái,...
 * ------------------------------------------------------------
 * Người cập nhật:
 * Ngày cập nhật:
 * ------------------------------------------------------------
 * Nội dung cập nhật:
 *
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CamelCaseScreen(navController: NavController) {
    // Lấy context từ Composable
    val context = LocalContext.current
    // Kiểm tra thiết bị là tablet hay mobile
    val isTablet = isTablet(context)
    // Đặt giá trị padding
    val paddingValue = if (isTablet) 270.dp else 50.dp

    // Tạo trạng thái cho TextField
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    // Tạo trạng thái cho Dropdown
    var isDropdownExpanded by remember { mutableStateOf(false) }
    var selectedStatus by remember { mutableStateOf("Hoat động") }

    val dsCamelCase = remember {
        mutableStateOf(
            listOf(
                CamelCase(
                    productId = 1,
                    productName = "Smart Light Bulb",
                    productCategory = "Lighting",
                    costPrice = 100000.0,
                    sellingPrice = 200000.0,
                    soldQuantity = 150,
                    stockQuantity = 300,
                    productDescription = "A smart light bulb with adjustable brightness and color temperature.",
                    technicalSpecifications = "Wi-Fi enabled, 9W power consumption, compatible with Alexa and Google Assistant.",
                    state = "Hoạt động",
                    image = "https://via.placeholder.com/150"
                ),
                CamelCase(
                    productId = 2,
                    productName = "Smart Thermostat",
                    productCategory = "Smart Home",
                    costPrice = 100000.0,
                    sellingPrice = 200000.0,
                    soldQuantity = 150,
                    stockQuantity = 300,
                    productDescription = "A smart thermostat to control the temperature of your home remotely.",
                    technicalSpecifications = "Wi-Fi enabled, temperature range 15-30°C, works with Alexa and Google Assistant.",
                    state = "Hoạt động",
                    image = "https://via.placeholder.com/150"
                ),
                CamelCase(
                    productId = 3,
                    productName = "Security Camera",
                    productCategory = "Security",
                    costPrice = 100000.0,
                    sellingPrice = 200000.0,
                    soldQuantity = 150,
                    stockQuantity = 300,
                    productDescription = "A high-definition security camera with motion detection and night vision.",
                    technicalSpecifications = "1080p resolution, 2-way audio, motion detection, cloud storage.",
                    state = "Hoạt động",
                    image = "https://via.placeholder.com/150"
                ),
                CamelCase(
                    productId = 4,
                    productName = "Smart Door Lock",
                    productCategory = "Security",
                    costPrice = 100000.0,
                    sellingPrice = 200000.0,
                    soldQuantity = 150,
                    stockQuantity = 300,
                    productDescription = "A smart door lock with fingerprint and keyless entry for enhanced security.",
                    technicalSpecifications = "Fingerprint recognition, Bluetooth enabled, battery-powered.",
                    state = "Không hoạt động",
                    image = "https://via.placeholder.com/150"
                )
            )
        )
    }

    // Lọc danh sách khi có sự thay đổi trong tìm kiếm
    val filteredCamelCase = dsCamelCase.value.filter {
        it.productName.contains(searchText.text, ignoreCase = true) ||
                it.state.contains(searchText.text, ignoreCase = true)||
                it.productCategory.contains(searchText.text, ignoreCase = true)||
                it.productDescription.contains(searchText.text, ignoreCase = true)||
                it.technicalSpecifications.contains(searchText.text, ignoreCase = true)
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
                            text = "Danh Sách Sản phẩm",
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

                // Dropdown và Nút "Thêm Sản phầm"
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Nút "Thêm Danh mục"
                    Button(
                        onClick = { /* Handle add  */ },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("THÊM SẢN PHẨM")
                    }

                    // Dropdown trạng thái
                    Box(modifier = Modifier.weight(1f)) {
                        OutlinedButton(
                            onClick = { isDropdownExpanded = !isDropdownExpanded },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = selectedStatus)
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
                                        selectedStatus = status
                                        isDropdownExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }

                // Danh sách Danh mục
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(filteredCamelCase.size) { camelcase ->
                        val camelcase = filteredCamelCase[camelcase]
                        CamelCaseItem(camelcase =camelcase,
                            onClick = {
                                navController.navigate("camelcase_detail_screen/${camelcase.productId}")
                            })
                    }
                }
            }
        }
    )
}
@Composable
fun CamelCaseItem(camelcase: CamelCase,onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(1.dp, Color.Gray)
            .padding(16.dp)
            .clickable(onClick= onClick)
    ) {
        // Hiển thị hình ảnh
        Image(
            painter = rememberImagePainter(camelcase.image),
            contentDescription = null,
            modifier = Modifier.size(60.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            // Tên sản phẩm
            Text(
                text = "Tên: ${camelcase.productName}",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )

            // Giá bán
            Text(
                text = "Giá bán: ${camelcase.sellingPrice}",
                style = MaterialTheme.typography.bodySmall
            )

            // Thông số kỹ thuật
            Text(
                text = "Thông số kỹ thuật: ${camelcase.technicalSpecifications}",
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,// Số dòng tối đa hiển thị
                overflow = TextOverflow.Ellipsis // Hiển thị ... khi dài
            )
        }

        // Hiển thị trạng thái
        Text(
            modifier = Modifier.fillMaxWidth().weight(1f),
            text = "${camelcase.state}",
            style = MaterialTheme.typography.bodySmall,
            color = if (camelcase.state == "Hoạt động") Color(0xFF006400) else Color.Red,
            textAlign = TextAlign.End,

            )
    }
}

