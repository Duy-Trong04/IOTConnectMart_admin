package com.example.iotconnectmart_admin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.iotconnectmart_admin.screen.Dashboard.DashboardScreen
import com.example.iotconnectmart_admin.screen.ImportProduct.ImportProductListScreen
import com.example.iotconnectmart_admin.screen.ManageAttributes.attributeList.AttributeListScreen
import com.example.iotconnectmart_admin.screen.statistical.StatisticsScreen
import com.example.iotconnectmart_admin.ui.theme.IOTConnectMart_adminTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController:NavController){
    val navdrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope() //xử lý suspending fun (mở và đóng drawer)
    val countries = listOf(
        "DashBoard",
        "Thống kê",
        "Khách hàng",
        "Đơn hàng",
        "Danh mục",
        "Thuộc tính",
        "Sản phẩm",
        "Nhập hàng",
        "Nhân viên",
        "SlideShow",
    )
    var selectedTabIndex by remember { mutableStateOf(0) }// Lưu trạng thái tab được chọn


    ModalNavigationDrawer(
        drawerState = navdrawerState,
        drawerContent = {
            ModalDrawerSheet {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF5D9EFF))
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "IOT Connect Mart",
                        modifier = Modifier.padding(5.dp),
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Đóng danh mục",
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .size(24.dp)
                            .clickable {
                                // thoát danh mục
                                scope.launch {
                                    navdrawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            },
                        tint = Color.White
                    )
                }
                HorizontalDivider()
                countries.forEachIndexed { index, country ->
                    NavigationDrawerItem(
                        label = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        vertical = 5.dp,
                                        horizontal = 5.dp
                                    )
                                    .drawBehind {
                                        drawLine(
                                            color = Color.Black,
                                            start = Offset(0f, size.height),
                                            end = Offset(size.width, size.height),
                                            strokeWidth = 1.dp.toPx()
                                        )
                                    }
                            ) {
                                Text(text = country)
                            }
                        },
                        selected = selectedTabIndex == index,
                        onClick = {
                            // Cập nhật tab được chọn khi người dùng nhấn vào mục menu
                            selectedTabIndex = index
                            // Đóng Navigation Drawer sau khi chọn một mục
                            scope.launch {
                                navdrawerState.close()
                            }
                        }
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "IOT Connect Mart",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(imageVector = Icons.Filled.Notifications,
                                contentDescription = "thong bao"
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                // mở và đóng drawer
                                navdrawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF5D9EFF),
                        navigationIconContentColor = Color.White,
                        titleContentColor = Color.White,
                        actionIconContentColor = Color.White
                    )
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            )
            {
                ScrollableTabRow(
                    selectedTabIndex = selectedTabIndex,
                    edgePadding = 0.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    countries.forEachIndexed { index, country ->
                        Tab(
                            text = { Text(country) },
                            selected = selectedTabIndex == index,
                            onClick = {
                                selectedTabIndex = index // Cập nhật trạng thái tab khi người dùng nhấn vào
                            },
                            modifier = Modifier.weight(1f),
                            selectedContentColor = Color(0xFF5F9EFF),
                            unselectedContentColor = Color.Black,
                        )
                    }
                }
                when (selectedTabIndex) {
                    0 -> {
                        DashboardScreen(navController = navController)
                    }
                    1 -> {
                        StatisticsScreen(navController = navController)
                    }
                    2 -> {
                        CustomerScreen(navController = navController)
                    }
                    3 -> {
                        OrderScreen(navController = navController)
                    }
                    4 -> {
                        CategoryScreen(navController = navController)
                    }
                    5 ->{
                        AttributeListScreen(navController = navController)
                    }
                    6 ->{
                        CamelCaseScreen(navController = navController)
                    }
                    7 ->{
                        ImportProductListScreen(navController = navController) {}
                    }
                    8 -> {
                        EmployeeScreen(navController = navController)
                    }
                    9 -> {
                        SlideShowScreen(navController = navController)
                    }
                    // Thêm các phần tử cho các tab khác nếu cần thiết
                }
            }
        }
    }
}
