package com.example.iotconnectmart_admin

import android.content.Context
import android.content.res.Configuration
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
import androidx.compose.ui.unit.times
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import coil.compose.rememberImagePainter

/** Giao diện màn hình Danh sách SlideShow (SlideShowScreen)
 * -------------------------------------------
 * Người code: Văn Nam Cao
 * Ngày viết: 17/12/2024
 * Lần cập nhật cuối cùng: 23/12/2024
 * -------------------------------------------
 * Input:
 *
 * Output: Chứa các thành phần giao diện của màn hình Danh sách SlideShow, bao gồm
 *  Danh sách các SlideShow, tìm kiếm, Dropdown và nút "Thêm SlideShow", nút Lưu
 * ------------------------------------------------------------
 * Người cập nhật:
 * Ngày cập nhật:
 * ------------------------------------------------------------
 * Nội dung cập nhật:
 *
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SlideShowScreen(navController: NavController) {
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

    val dsSlideShow = remember {
        mutableStateOf(
            listOf(
                ProductSlideShow(
                    id = 1,
                    slideTitle = "Slide 1",
                    label = "Hoạt động",
                    targetUrl = "http://example.com/1",
                    postedDate = "2024-12-01",
                    endDate = "2024-12-31",
                    shortContent = "Nội dung ngắn cho Slide 1",
                    state = "Hoạt động",
                    image = "https://via.placeholder.com/150"
                ),
                ProductSlideShow(
                    id = 2,
                    slideTitle = "Slide 2",
                    label = "Không hoạt động",
                    targetUrl = "http://example.com/2",
                    postedDate = "2024-11-25",
                    endDate = "2024-12-15",
                    shortContent = "Nội dung ngắn cho Slide 2",
                    state = "Không hoạt động",
                    image = "https://via.placeholder.com/150"
                ),
                ProductSlideShow(
                    id = 3,
                    slideTitle = "Slide 3",
                    label = "Hoạt động",
                    targetUrl = "http://example.com/3",
                    postedDate = "2024-12-05",
                    endDate = "2024-12-20",
                    shortContent = "Nội dung ngắn cho Slide 3",
                    state = "Hoạt động",
                    image = "https://via.placeholder.com/150"
                ),
                ProductSlideShow(
                    id = 4,
                    slideTitle = "Slide 4",
                    label = "Hoạt động",
                    targetUrl = "http://example.com/3",
                    postedDate = "2024-12-05",
                    endDate = "2024-12-20",
                    shortContent = "Nội dung ngắn cho Slide 9",
                    state = "Không hoạt động",
                    image = "https://via.placeholder.com/150"
                ),

            )
        )
    }

    // Lọc danh sách khi có sự thay đổi trong tìm kiếm
    val filteredSlideShow = dsSlideShow.value.filter {
        it.slideTitle.contains(searchText.text, ignoreCase = true) ||
                it.state.contains(searchText.text, ignoreCase = true)||
                it.label.contains(searchText.text, ignoreCase = true)||
                it.shortContent.contains(searchText.text, ignoreCase = true)||
                it.postedDate.contains(searchText.text, ignoreCase = true)||
                it.endDate.contains(searchText.text, ignoreCase = true)||
                it.targetUrl.contains(searchText.text, ignoreCase = true)
    }

    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        verticalAlignment = Alignment.CenterVertically // Căn chỉnh theo chiều dọc
//                    ) {
//                        IconButton(
//                            onClick = {
//                                // Quay về màn hình Trang chủ
//                            },
//                        ) {
//                            Icon(
//                                imageVector = Icons.Default.ArrowBack,
//                                contentDescription = "Quay về",
//                                tint = Color.White
//                            )
//                        }
//
//                        Spacer(modifier = Modifier.weight(1f)) // Để tiêu đề chiếm phần còn lại
//
//                        Text(
//                            text = "Danh Sách SlideShow",
//                            fontWeight = FontWeight.Bold,
//                            modifier = Modifier.fillMaxWidth().padding(start = paddingValue),
//                        )
//                    }
//                },
//                colors = TopAppBarDefaults.smallTopAppBarColors(
//                    containerColor = Color(0xFF5D9EFF),
//                    titleContentColor = Color.White
//                ),
//            )
//        },
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
                        .padding(8.dp)
                        .border(1.dp, color = Color.Gray, RoundedCornerShape(10.dp)),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Dropdown và Nút "Thêm SlideShow"
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Nút "Thêm SlideShow"
                    Button(
                        onClick = { navController.navigate(Screen.AddSlideShowScreen.route) },
                        modifier = Modifier.width(withValue).size(64.dp).padding(top=9.dp, start = 6.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF5D9EFF),
                            contentColor = Color.White
                        ), shape = RectangleShape
                    ) {
                        Text("THÊM SLIDESHOW")
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

                // Danh sách SlideShow
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(filteredSlideShow.size) { slide ->
                        val slide = filteredSlideShow[slide]
                        SlideShowItem(slide = slide,
                            onClick = {
                                navController.navigate("slide_show_detail_screen/${slide.id}")
                            })
                    }
                }
            }
        }
    )
}
@Composable
fun SlideShowItem(slide: ProductSlideShow,onClick: () -> Unit) {
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
            painter = rememberImagePainter(slide.image),
            contentDescription = null,
            modifier = Modifier.size(60.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))
        Column() {
            // Tiêu đề
            Text(
                text = "Tiêu đề: ${slide.slideTitle}",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium
            )

            // Ngày đăng
            Text(
                text = "Ngày đăng: ${slide.postedDate}",
                style = MaterialTheme.typography.bodySmall
            )

            // Ngày kết thúc
            Text(
                text = "Ngày kết thúc: ${slide.endDate}",
                style = MaterialTheme.typography.bodySmall
            )
        }

        // Hiển thị trạng thái
        Text(
            text = "${slide.state}",
            style = MaterialTheme.typography.bodySmall,
            color = if (slide.state == "Hoạt động") Color(0xFF006400) else Color.Red,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

// Hàm kiểm tra thiết bị có phải là một chiếc máy tính bảng hay không
fun isTablet(context: Context): Boolean {
    return (context.resources.configuration.screenLayout
            and Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE
}
