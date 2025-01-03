package com.example.iotconnectmart_admin.screen.Dashboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

/** Man hình Dashboard
 * -------------------------------------------
 * Người code: Nguyễn Mạnh Cường
 * Ngày viết: 18/12/2024
 * Lần cập nhật cuối cùng: 18/12/2024
 * -------------------------------------------
 * Input: chưa có
 *
 * Output: Chứa các thành phần đối tượng màn hình
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
fun DashboardScreen(navController: NavController) {
    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("DashBoard") },
//                navigationIcon = {
//                    IconButton(onClick = { /* Xử lý quay lại */ navController.popBackStack()}) {
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
            LazyColumn (
                modifier = Modifier
                    .fillMaxSize()
                    //.padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item { RevenueSection() }
                item { MonthlyOrdersSection() }
                item {  InventorySection() }
                item {  ChartSection()  }
                item { OrderStatusTable()  }
                item {  TopProductsSection()}
                item {  TopEmployeesSection() }
            }
        }
    )
}
/*
* Chua co tham so
* */
@Composable
fun RevenueSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text("Doanh thu tháng 11: 1.000.000 VND", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text("Tháng 10: 800.000 VND", fontSize = 14.sp)
        Text("Tăng trưởng: ...%", fontSize = 14.sp, color = Color.Green)
    }
}
/*
* Chua co tham so
* */
@Composable
fun MonthlyOrdersSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text("Đơn hàng tháng 11: Số lượng: 80", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text("Tháng 10: Số lượng 100", fontSize = 14.sp)
        Text("Tăng trưởng: ...%", fontSize = 14.sp, color = Color.Red)
    }
}
/*
* Chua co tham so
* */
@Composable
fun InventorySection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text("Tồn kho tháng 11: Số lượng 300", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text("Tháng 10: Số lượng 200", fontSize = 14.sp)
        Text("Tăng trưởng: ...%", fontSize = 14.sp, color = Color.Green)
    }
}
/*
* Chua co tham so
* */
@Composable
fun ChartSection() {
    val data = listOf(
        Pair("Tháng 1", 10f),
        Pair("Tháng 2", 20f),
        Pair("Tháng 3", 30f),
        Pair("Tháng 4", 40f)
    )
    val maxValue = data.maxOf { it.second }
    val barWidth = 40.dp
    val spaceBetweenBars = 16.dp

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 16.dp)
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val barSpacing = barWidth.toPx() + spaceBetweenBars.toPx()

        data.forEachIndexed { index, pair ->
            val barHeight = (pair.second / maxValue) * canvasHeight

            // Vẽ thanh biểu đồ
            drawRoundRect(
                color = Color.Blue,
                size = Size(barWidth.toPx(), barHeight),
                topLeft = Offset(
                    x = index * barSpacing,
                    y = canvasHeight - barHeight
                ),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(4.dp.toPx())
            )

            // Vẽ tên trục x
            drawContext.canvas.nativeCanvas.drawText(
                pair.first,
                index * barSpacing + barWidth.toPx() / 2,
                canvasHeight + 16.dp.toPx(),
                android.graphics.Paint().apply {
                    textAlign = android.graphics.Paint.Align.CENTER
                    color = android.graphics.Color.BLACK
                    textSize = 32f
                }
            )

            // Vẽ giá trị trên thanh
            drawContext.canvas.nativeCanvas.drawText(
                pair.second.toString(),
                index * barSpacing + barWidth.toPx() / 2,
                canvasHeight - barHeight - 8.dp.toPx(),
                android.graphics.Paint().apply {
                    textAlign = android.graphics.Paint.Align.CENTER
                    color = android.graphics.Color.BLACK
                    textSize = 32f
                }
            )
        }
    }
}
/*
* Chua co tham so
* */
@Composable
fun OrderStatusTable() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text("Trạng thái đơn hàng", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Đơn hàng", fontWeight = FontWeight.Bold)
            Text("Số lượng", fontWeight = FontWeight.Bold)
            Text("Doanh thu", fontWeight = FontWeight.Bold)
        }
        val orderStatuses = listOf(
            Triple("Mới", "50", "500000 VND"),
            Triple("Đã vận chuyển", "30", "300000 VND"),
            Triple("Thành công", "20", "200000 VND"),
            Triple("Thất bại", "10", "100000 VND"),
            Triple("Hủy", "5", "50000 VND")
        )
        orderStatuses.forEach { status ->
            OrderStatusItem(status.first, status.second, status.third)
        }
    }
}

@Composable
fun OrderStatusItem(status: String, quantity: String, revenue: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(status)
        Text(quantity)
        Text(revenue)
    }
}

/*
* Chua co tham so
* */
@Composable
fun TopProductsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text("Top sản phẩm", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        val topProducts = listOf(
            Triple("Sản phẩm 1", "100", "1000000 VND"),
            Triple("Sản phẩm 2", "80", "800000 VND"),
            Triple("Sản phẩm 3", "50", "500000 VND")
        )
        topProducts.forEach { product ->
            TopProductItem(
                product.first,
                product.second,
                product.third
            )
        }
    }
}

@Composable
fun TopProductItem(productName: String, quantity: String, revenue: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(productName)
        Text(quantity)
        Text(revenue)
    }
}

@Composable
fun TopEmployeesSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text("Top Nhân viên", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        val topEmployees = listOf(
            Triple("Nhân viên 1", "50", "500000 VND"),
            Triple("Nhân viên 2", "40", "400000 VND"),
            Triple("Nhân viên 3", "30", "300000 VND")
        )
        topEmployees.forEach { employee ->
            TopEmployeeItem(employee.first, employee.second, employee.third)
        }
    }
}

@Composable
fun TopEmployeeItem(employeeName: String, quantity: String, revenue: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(employeeName)
        Text(quantity)
        Text(revenue)
    }
}

