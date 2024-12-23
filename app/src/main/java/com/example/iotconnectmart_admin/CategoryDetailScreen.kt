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
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import java.util.Calendar
import android.app.DatePickerDialog
import androidx.compose.ui.focus.FocusRequester

/** Giao diện màn hình Chi tiết Danh mục (CategoryDetailScreen)
 * -------------------------------------------
 * Người code: Văn Nam Cao
 * Ngày viết: 18/12/2024
 * Lần cập nhật cuối cùng: 23/12/2024
 * -------------------------------------------
 * Input:
 *
 * Output: Chứa các thành phần giao diện của màn hình thông tin
 *  của 1 Danh mục, bao gồm ảnh, Mã danh mục, tên danh mục,...
 * ------------------------------------------------------------
 * Người cập nhật:
 * Ngày cập nhật:
 * ------------------------------------------------------------
 * Nội dung cập nhật:
 *
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDetailScreen(idC: Int?, navController: NavController) {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    // Tạo trạng thái cho Dropdown
    var isDropdownExpanded by remember { mutableStateOf(false) }
    // Lấy context từ Composable
    val context = LocalContext.current
    // Kiểm tra thiết bị là tablet hay mobile
    val isTablet = isTablet(context)
    // Đặt giá trị padding
    val paddingValue = if (isTablet) 270.dp else 50.dp
    // Kiểm tra id có hợp lệ hay không
    if (idC == null) {
        Text("Không tìm thấy Danh mục")
        return
    }


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


    // Lấy thông tin Danh mục từ id (ví dụ: từ danh sách hoặc API)
    val category = remember {
        dsCategory.value.firstOrNull { it.idC == idC }
    }

    if (category == null) {
        Text("Danh mục không tồn tại")
        return
    }

    var CategoryId: String by remember { mutableStateOf<String>(category.categoryId) }
    var CategoryName: String by remember { mutableStateOf<String>(category.categoryName) }
    var CategoryCreatedBy: String by remember { mutableStateOf<String>(category.createdBy) }
    var CategoryParentCategory: String by remember { mutableStateOf<String>(category.parentCategory) }
    var CategoryCreationDate: String by remember { mutableStateOf<String>(category.creationDate) }
    var CategoryDescription: String by remember { mutableStateOf<String>(category.categoryDescription) }
    var selectedStatus: String by remember { mutableStateOf<String>(category.state) }

    // Tạo FocusRequester để theo dõi focus của TextField
    val focusRequester = remember { FocusRequester() }

    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, selectedYear, selectedMonth, selectedDay ->
                // Cập nhật SlideDate sau khi chọn ngày
                CategoryCreationDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
            },
            year, month, day
        )
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
                                // Quay về màn hình danh sách Category
                                navController.navigate(Screen.CategoryScreen.route)
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
                            text = "Chi Tiết Danh Mục",
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
            // Hiển thị chi tiết Category
            LazyColumn(
                modifier = Modifier.padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                category?.let {
                    item {
                        // Hiển thị ảnh
                        Image(
                            painter = rememberImagePainter(category.image),
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth().size(130.dp),
                            alignment = Alignment.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        // Mã danh mục
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Mã danh mục: ", fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(10.dp))
                            BasicTextField(
                                value = CategoryId,
                                onValueChange = { newValue -> CategoryId = newValue },
                                textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                                modifier = Modifier.fillMaxWidth()
                                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                    .padding(16.dp)

                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        // Tên danh mục
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Tên danh mục: ", fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(3.dp))
                            BasicTextField(
                                value = CategoryName,
                                onValueChange = { newValue -> CategoryName = newValue },
                                textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                                modifier = Modifier.fillMaxWidth()
                                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                    .padding(16.dp)

                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        // Người tạo
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Người tạo: ", fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(35.dp))
                            BasicTextField(
                                value = CategoryCreatedBy,
                                onValueChange = { newValue -> CategoryCreatedBy = newValue },
                                textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                                modifier = Modifier.fillMaxWidth()
                                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                    .padding(16.dp)

                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        // Danh mục cha
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Danh mục cha: ", fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(1.dp))
                            BasicTextField(
                                value = CategoryParentCategory,
                                onValueChange = { newValue -> CategoryParentCategory = newValue },
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
                            Spacer(modifier = Modifier.width(41.dp))
                            BasicTextField(
                                value = CategoryCreationDate,
                                onValueChange = { newValue -> CategoryCreationDate = newValue },
                                textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                                modifier = Modifier.fillMaxWidth()
                                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                    .padding(16.dp)

                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        // Mô tả
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                            Text(text = "Mô tả: ", fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.width(62.dp))
                            BasicTextField(
                                value = CategoryDescription,
                                onValueChange = { newValue -> CategoryDescription = newValue },
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
        }
    )
}