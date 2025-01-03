package com.example.iotconnectmart_admin.screen.ImportProduct

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.iotconnectmart_admin.Screen
import com.example.iotconnectmart_admin.data.ImportProduct




/** Man hình  nhập hàng (ImportProductListScreen())
 * -------------------------------------------
 * Người code: Nguyễn Mạnh Cường
 * Ngày viết: 18/12/2024
 * Lần cập nhật cuối cùng: 18/12/2024
 * -------------------------------------------
 * Input: chưa có
 *
 * Output: Chứa các thành phần đối tượng màn hình và danh sách nhập hàng
 * ------------------------------------------------------------
 * Người cập nhật:
 * Ngày cập nhật:
 * ------------------------------------------------------------
 * Nội dung cập nhật:
 *
 */
@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true)
@Composable
fun ImportProductListScreen(navController:NavController,onProductClick: (String) -> Unit) {
    var searchQuery by remember { mutableStateOf("") }

    var selectedStatus by remember { mutableStateOf("Tất cả") }
    var isDropdownExpanded by remember { mutableStateOf(false) }
    val statusOptions = listOf( "Tất cả", "Đang vận chuyển", "Đã nhận", "Đang xử lý", "Đã hủy", "Chờ xác nhận", "Chờ thanh toán", "Đã thanh toán" )

    val importProducts = listOf(
        ImportProduct("NH001", "Văn Nam Cao", "11/01/2000", "Sản phẩm A", "file1.xlsx", "9.000.000 VND", "100", "900.000.000 VND", "Lô hàng đầu tiên", "Đang vận chuyển", "https://example.com/image1.jpg"),
        ImportProduct("NH002", "Nguyễn Thị Bích", "15/03/2021", "Sản phẩm B", "file2.xlsx", "5.000.000 VND", "50", "250.000.000 VND", "Lô hàng tháng 3", "Đã nhận", "https://example.com/image2.jpg"),
        ImportProduct("NH003", "Trần Quốc Anh", "20/05/2022", "Sản phẩm C", "file3.xlsx", "7.500.000 VND", "75", "562.500.000 VND", "Lô hàng tháng 5", "Đang xử lý", "https://example.com/image3.jpg"),
        ImportProduct("NH004", "Phạm Thanh Hùng", "02/12/2019", "Sản phẩm D", "file4.xlsx", "10.000.000 VND", "100", "1.000.000.000 VND", "Lô hàng tháng 12", "Đã hủy", "https://example.com/image4.jpg"),
        ImportProduct("NH005", "Ngô Thị Hương", "14/07/2021", "Sản phẩm E", "file5.xlsx", "12.000.000 VND", "120", "1.440.000.000 VND", "Lô hàng tháng 7", "Đang vận chuyển", "https://example.com/image5.jpg"),
        ImportProduct("NH006", "Lê Văn Bình", "23/03/2023", "Sản phẩm F", "file6.xlsx", "6.000.000 VND", "60", "360.000.000 VND", "Lô hàng tháng 3", "Đã nhận", "https://example.com/image6.jpg"),
        ImportProduct("NH007", "Đặng Thu Hà", "09/11/2020", "Sản phẩm G", "file7.xlsx", "8.500.000 VND", "85", "722.500.000 VND", "Lô hàng tháng 11", "Đang xử lý", "https://example.com/image7.jpg"),
        ImportProduct("NH008", "Nguyễn Thị Minh", "17/04/2022", "Sản phẩm H", "file8.xlsx", "4.000.000 VND", "40", "160.000.000 VND", "Lô hàng tháng 4", "Đang vận chuyển", "https://example.com/image8.jpg"),
        ImportProduct("NH009", "Phan Minh Quân", "05/10/2021", "Sản phẩm I", "file9.xlsx", "3.500.000 VND", "35", "122.500.000 VND", "Lô hàng tháng 10", "Đã nhận", "https://example.com/image9.jpg")
    )

    val filteredProducts = if (selectedStatus == "Tất cả") {
        importProducts
    } else {
        importProducts.filter { it.status == selectedStatus }
    }
    //Lay kich thuoc man hinh
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Danh sách nhập hàng") },
//                navigationIcon = {
//                    IconButton(onClick = { /*  */navController.popBackStack() }) {
//                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Quay lại", tint = Color.White)
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color(0xFF5F9EFF), // Màu nền
//                    titleContentColor = Color.White // Màu văn bản tiêu đề
//                )
//            )
//        },
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

                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    ExposedDropdownMenuBox(
                        expanded = isDropdownExpanded,
                        onExpandedChange = { isDropdownExpanded = !isDropdownExpanded },
                        modifier = Modifier.width((1/3f).times(screenWidth))
                    ) {
                        OutlinedTextField(
                            readOnly = true,
                            value = selectedStatus,
                            onValueChange = { },
                            label = { Text("Trạng thái") },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDropdownExpanded) },
                            modifier = Modifier.menuAnchor()
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
                    Box {
                        Button(
                            onClick = { /* Chức năng của nút */  navController.navigate(Screen.ImportProductDetail.route)},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF5F9EFF)),
                            shape = RectangleShape,
                            modifier = Modifier.width((1/3f).times(screenWidth))
                        ) { Text("Thêm", color = Color.White) }
                    }

                }

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(filteredProducts) { importProduct ->
                           ImportProductCard(importProduct, onProductClick)
                    }
                }
            }
        }
    )
}

@Composable
fun ImportProductCard(importProduct: ImportProduct,onProductClick: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(16.dp)
            .clickable{onProductClick(importProduct.id)}
    ) {
        Image(
            painter = rememberAsyncImagePainter(importProduct.image),
            contentDescription = "Biểu tượng",
            modifier = Modifier.size(80.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text("ID: ${importProduct.id}", fontWeight = FontWeight.Bold)
            Text("Người tạo: ${importProduct.creator}")
            Text("Ngày nhập: ${importProduct.importDate}")
            Text("Tổng tiền: ${importProduct.totalAmount}")
            Text("Trạng thái: ${importProduct.status}")
        }
    }
}
