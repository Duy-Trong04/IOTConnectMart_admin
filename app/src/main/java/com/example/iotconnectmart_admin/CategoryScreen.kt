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

/** Giao diện màn hình Danh sách Danh mục (CategoryScreen)
 * -------------------------------------------
 * Người code: Văn Nam Cao
 * Ngày viết: 18/12/2024
 * Lần cập nhật cuối cùng: 23/12/2024
 * -------------------------------------------
 * Input:
 *
 * Output: Chứa các thành phần giao diện của màn hình Danh sách Danh mục, bao gồm
 *  Danh sách các Danh mục, tìm kiếm, Dropdown và nút "Thêm Danh mục", nút Lưu
 * ------------------------------------------------------------
 * Người cập nhật:
 * Ngày cập nhật:
 * ------------------------------------------------------------
 * Nội dung cập nhật:
 *
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(navController: NavController) {
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

    val dsCategory = remember {
        mutableStateOf(
            listOf(
                ProductCategory(
                    idC = 1,
                    categoryId = "CAT001",
                    categoryName = "Smart Home",
                    createdBy = "Admin",
                    parentCategory = "Đèn",
                    creationDate = "2024-12-01",
                    categoryDescription = "Devices and systems for home automation.",
                    state = "Hoạt động",
                    image = "https://via.placeholder.com/150"
                ),
                ProductCategory(
                    idC = 2,
                    categoryId = "CAT002",
                    categoryName = "Lighting",
                    createdBy = "Admin",
                    parentCategory = "Đèn",
                    creationDate = "2024-12-02",
                    categoryDescription = "Smart lighting systems for homes and offices.",
                    state = "Không hoạt động",
                    image = "https://via.placeholder.com/150"
                ),
                ProductCategory(
                    idC = 3,
                    categoryId = "CAT003",
                    categoryName = "Security",
                    createdBy = "Admin",
                    parentCategory = "Đèn",
                    creationDate = "2024-12-03",
                    categoryDescription = "Smart security systems including cameras and alarms.",
                    state = "Hoạt động",
                    image = "https://via.placeholder.com/150"
                ),
                ProductCategory(
                    idC = 4,
                    categoryId = "CAT004",
                    categoryName = "Entertainment",
                    createdBy = "Admin",
                    parentCategory = "Đèn",
                    creationDate = "2024-12-04",
                    categoryDescription = "Smart entertainment systems for audio and video.",
                    state = "Không hoạt động",
                    image = "https://via.placeholder.com/150"
                )
            )
        )
    }

    // Lọc danh sách khi có sự thay đổi trong tìm kiếm
    val filteredCategory = dsCategory.value.filter {
        it.categoryId.contains(searchText.text, ignoreCase = true) ||
                it.state.contains(searchText.text, ignoreCase = true)||
                it.categoryName.contains(searchText.text, ignoreCase = true)||
                it.parentCategory.contains(searchText.text, ignoreCase = true)||
                it.creationDate.contains(searchText.text, ignoreCase = true)||
                it.categoryDescription.contains(searchText.text, ignoreCase = true)||
                it.createdBy.contains(searchText.text, ignoreCase = true)
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
                            text = "Danh Sách Danh Mục",
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

                // Dropdown và Nút "Thêm Danh mục"
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Nút "Thêm Danh mục"
                    Button(
                        onClick = { /* Handle add  */ },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("THÊM DANH MỤC")
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
                    items(filteredCategory.size) { category ->
                        val category = filteredCategory[category]
                        CategoryItem(category =category,
                            onClick = {
                                navController.navigate("category_detail_screen/${category.idC}")
                            })
                    }
                }
            }
        }
    )
}
@Composable
fun CategoryItem(category: ProductCategory,onClick: () -> Unit) {
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
            painter = rememberImagePainter(category.image),
            contentDescription = null,
            modifier = Modifier.size(60.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            // Tên danh mục
            Text(
                text = "Tên: ${category.categoryName}",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )

            // Ngày tạo
            Text(
                text = "Ngày Tạo: ${category.creationDate}",
                style = MaterialTheme.typography.bodySmall
            )

            // MoTa
            Text(
                text = "Mô tả: ${category.categoryDescription}",
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,// Số dòng tối đa hiển thị
                overflow = TextOverflow.Ellipsis // Hiển thị ... khi dài
            )
        }

        // Hiển thị trạng thái
        Text(
            modifier = Modifier.fillMaxWidth().weight(1f),
            text = "${category.state}",
            style = MaterialTheme.typography.bodySmall,
            color = if (category.state == "Hoạt động") Color(0xFF006400) else Color.Red,
            textAlign = TextAlign.End,

        )
    }
}

