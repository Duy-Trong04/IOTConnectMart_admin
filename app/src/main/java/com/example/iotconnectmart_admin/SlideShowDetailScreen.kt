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

/** Giao diện màn hình Chi tiết SlideShow (SlideShowDetailScreen)
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
fun SlideShowDetailScreen(id: Int?, navController: NavController) {
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
    if (id == null) {
        Text("Không tìm thấy SlideShow")
        return
    }
    // Lưu trữ giá trị của ngày
    //var Slidedate by remember { mutableStateOf("Chọn ngày") }



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


    // Lấy thông tin SlideShow từ id (ví dụ: từ danh sách hoặc API)
    val slide = remember {
        dsSlideShow.value.firstOrNull { it.id == id }
    }

    if (slide == null) {
        Text("SlideShow không tồn tại")
        return
    }

    var SlideTitle: String by remember { mutableStateOf<String>(slide.slideTitle) }
    var SlideLabel: String by remember { mutableStateOf<String>(slide.label) }
    var SlideTargetUrl by remember { mutableStateOf<String>(slide.targetUrl) }
    var SlideDate: String by remember { mutableStateOf<String>(slide.postedDate) }
    var SlideEndDate: String by remember { mutableStateOf<String>(slide.endDate) }
    var SlideShortContent: String by remember { mutableStateOf<String>(slide.shortContent) }
    var selectedStatus: String by remember { mutableStateOf<String>(slide.state) }

    // Tạo FocusRequester để theo dõi focus của TextField
    val focusRequester = remember { FocusRequester() }

    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, selectedYear, selectedMonth, selectedDay ->
                // Cập nhật SlideDate sau khi chọn ngày
                SlideDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
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
                                // Quay về màn hình danh sách SlideShow
                                navController.navigate(Screen.SlideShowScreen.route)
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
                            text = "Chi Tiết SlideShow",
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
                slide?.let {
                    item {
                        // Hiển thị ảnh
                        Image(
                            painter = rememberImagePainter(slide.image),
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
        }
    )
}