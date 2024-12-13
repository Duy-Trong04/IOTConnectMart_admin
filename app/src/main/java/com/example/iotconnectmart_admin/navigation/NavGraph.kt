package com.example.iotconnectmart_admin.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.iotconnectmart_admin.data.ImportProduct
import com.example.iotconnectmart_admin.screen.Dashboard.DashboardScreen
import com.example.iotconnectmart_admin.screen.ImportProduct.ImportProductDetailScreen
import com.example.iotconnectmart_admin.screen.ImportProduct.ImportProductListScreen
import com.example.iotconnectmart_admin.screen.ManageAttributes.attributeList.AttributeListScreen
import com.example.iotconnectmart_admin.screen.ManageAttributes.detailAttribute.AttributeDetailScreen
import com.example.iotconnectmart_admin.screen.ManageAttributes.detailGroupAttribute.AttributeGroupScreen
import com.example.iotconnectmart_admin.screen.statistical.StatisticsScreen

@Composable
fun NavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.AttributeListScreen.route,
    ) {

        //man hinh nhap hang
        composable(route = Screen.ImportProductList.route ) {
            ImportProductListScreen(navController = navController) { importProductID ->
                navController.navigate(Screen.ImportProductDetail.createRoute(importProductID))
            }
        }
        //Man hinh Chi tiet nhap hang
        composable(route = Screen.ImportProductDetail.route) {
            ImportProductDetailScreen(navController)
        }
        //man hinh danh sach thuoc tinh
        composable(route= Screen.AttributeListScreen.route){
            AttributeListScreen(navController)
        }
        //Man hinh chi tiet thuoc tinh
        composable(route= Screen.AttributeDetailScreen.route){
            AttributeDetailScreen(navController)
        }
        //Man hinh chi tiet nhom thuoc tinh
        composable(route= Screen.AttributeGroupScreen.route){
            AttributeGroupScreen(navController)
        }
        //Man hinh Thống kê
        composable(route= Screen.StatisticsScreen.route){
            StatisticsScreen(navController)
        }
        //Man hinh Dashboard
        composable(route= Screen.DashboardScreen.route){
            DashboardScreen(navController)
        }
    }
}
