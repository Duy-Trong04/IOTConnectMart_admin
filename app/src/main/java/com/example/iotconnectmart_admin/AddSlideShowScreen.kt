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
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.material3.TextField

/** Giao diện màn hình Thêm SlideShow (AddSlideShowScreen)
 * -------------------------------------------
 * Người code: Văn Nam Cao
 * Ngày viết: 17/12/2024
 * Lần cập nhật cuối cùng: 23/12/2024
 * -------------------------------------------
 * Input:
 *
 * Output: Chứa các thành phần giao diện của màn hình thông tin
 *  của 1 SlideShow, bao gồm ảnh, tiêu đề, nội dung nút,...
 * ------------------------------------------------------------
 * Người cập nhật:
 * Ngày cập nhật:
 * ------------------------------------------------------------
 * Nội dung cập nhật:
 *
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSlideShowScreen(navController: NavController) {

    // Lấy context từ Composable
    val context = LocalContext.current
    // Kiểm tra thiết bị là tablet hay mobile
    val isTablet = isTablet(context)
    // Đặt giá trị padding
    val paddingValue = if (isTablet) 280.dp else 70.dp
    // Tạo trạng thái cho Dropdown
    var isDropdownExpanded by remember { mutableStateOf(false) }

    var SlideTitle: String by remember { mutableStateOf<String>("") }
    var SlideLabel: String by remember { mutableStateOf<String>("") }
    var SlideTargetUrl by remember { mutableStateOf<String>("") }
    var SlideDate: String by remember { mutableStateOf<String>("") }
    var SlideEndDate: String by remember { mutableStateOf<String>("") }
    var SlideShortContent: String by remember { mutableStateOf<String>("") }
    var selectedStatus: String by remember { mutableStateOf<String>("Hoạt động") }


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
                                // Quay về màn hình danh sách SlideShow
                                navController.popBackStack()
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
                            text = "Thêm SlideShow",
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
            // Hiển thị chi tiết SlideShow
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
                    // Tiêu đề
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Tiêu đề: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(52.dp))
                        BasicTextField(
                            value = SlideTitle,
                            onValueChange = { newValue -> SlideTitle = newValue },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                            modifier = Modifier.fillMaxWidth()
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .padding(16.dp)

                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    // Nội dung nút
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Nội dung nút: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(12.dp))
                        BasicTextField(
                            value = SlideLabel,
                            onValueChange = { newValue -> SlideLabel = newValue },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                            modifier = Modifier.fillMaxWidth()
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .padding(16.dp)

                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    // Link đích
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Link đích: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(40.dp))
                        BasicTextField(
                            value = SlideTargetUrl,
                            onValueChange = { newValue -> SlideTargetUrl = newValue },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                            modifier = Modifier.fillMaxWidth()
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .padding(16.dp)

                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    // Ngày đăng
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Ngày đăng: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(29.dp))
//                            TextField(
//                                value = SlideDate,
//                                onValueChange = { newValue -> SlideDate = newValue },
//                                textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
//                                modifier = Modifier.fillMaxWidth()
//                                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
//                                    .padding(16.dp)
//                                    .focusRequester(focusRequester)
//                                    .onFocusChanged { focusState: FocusState ->
//                                    // Kiểm tra focus khi TextField nhận focus
//                                    if (focusState.isFocused) {
//                                        datePickerDialog.show() // Hiển thị DatePicker khi TextField nhận focus
//                                    }
//                                },
//                                readOnly = true, // Không cho phép chỉnh sửa trực tiếp
//                                imeAction = ImeAction.Done,
//                            )
                        BasicTextField(
                            value = SlideDate,
                            onValueChange = { newValue -> SlideDate = newValue },
                            textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                            modifier = Modifier.fillMaxWidth()
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .padding(16.dp)

                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    // Ngày kết thúc
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Ngày kết thúc: ", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(6.dp))
                        BasicTextField(
                            value = SlideEndDate,
                            onValueChange = { newValue -> SlideEndDate = newValue },
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
                        Text(text = "Nội dung ngắn: ", fontWeight = FontWeight.Bold)
                        BasicTextField(
                            value = SlideShortContent,
                            onValueChange = { newValue -> SlideShortContent = newValue },
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
                        Spacer(modifier = Modifier.width(32.dp))
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
                    // Nút lưu
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