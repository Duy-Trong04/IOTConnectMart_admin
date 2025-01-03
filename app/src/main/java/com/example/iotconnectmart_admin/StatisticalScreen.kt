package com.example.iotconnectmart_admin.screen.statistical


import androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController



/** Man hình Thống kê
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
fun StatisticsScreen(navController: NavController) {
    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Thống kê") },
//                navigationIcon = {
//                    IconButton(onClick = { /*  */navController.popBackStack() }) {
//                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Quay lại", tint = Color.White)
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color(0xFF5F9EFF),
//                    titleContentColor = Color.White
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
                item { StatisticsOverview() }
                item { BarChart() }
                item { TopProductsSection() }
                item {  EmployeesSection() }
            }
        }
    )
}

/*
* Chua co tham so
* Hien thi dữ liệu bán hàngư
*
* */
@Composable
fun StatisticsOverview() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        StatisticsBox("Hôm qua", "150", "+10%")
        StatisticsBox("Hôm nay", "180", "+20%")
        StatisticsBox("Tuần này", "1200", "+15%")
        StatisticsBox("Tháng này", "5000", "+25%")
    }
}


/*
* Chua co tham so
* */
@Composable
fun StatisticsBox(title: String, value: String, change: String) {
    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .padding(16.dp),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        Text(title, fontSize = 14.sp)
        Text(value, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(change, fontSize = 14.sp, color = Color.Green)
    }
}
/*
* Chua co tham so
* */
@Composable
fun BarChart() {
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
* Hàm Top Các sanr phẩm TopProductsSection
* */
@Composable
fun TopProductsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Text("Top sản phẩm", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Sản phẩm", fontWeight = FontWeight.Bold)
            Text("Số lượng", fontWeight = FontWeight.Bold)
            Text("Doanh thu", fontWeight = FontWeight.Bold)
        }
        // Dữ liệu giả lập, thêm nhiều dòng tuỳ theo dữ liệu thực tế
        TopProductItem("Product 1", "10", "10000 VND")
        TopProductItem("Product 2", "20", "20000 VND")
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
fun EmployeesSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Text("Nhân viên", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        // Placeholder cho thông tin nhân viên
        Text("Thông tin nhân viên sẽ được hiển thị ở đây.")
    }
}

