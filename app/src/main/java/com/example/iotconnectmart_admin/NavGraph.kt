package com.example.iotconnectmart_admin

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.iotconnectmart_admin.screen.Dashboard.DashboardScreen
import com.example.iotconnectmart_admin.screen.ImportProduct.ImportProductDetailScreen
import com.example.iotconnectmart_admin.screen.ImportProduct.ImportProductListScreen
import com.example.iotconnectmart_admin.screen.ManageAttributes.attributeList.AttributeListScreen
import com.example.iotconnectmart_admin.screen.ManageAttributes.detailAttribute.AttributeDetailScreen
import com.example.iotconnectmart_admin.screen.ManageAttributes.detailGroupAttribute.AttributeGroupScreen
import com.example.iotconnectmart_admin.screen.statistical.StatisticsScreen

@Composable
fun NavGraph(navController: NavHostController) {


    NavHost(
        navController = navController,
        startDestination = "HomeScreen"
    ) {
        composable("HomeScreen") {
            HomeScreen(navController)
        }
        composable("SlideShowScreen") {
            SlideShowScreen(navController)
        }
        composable(
            "slide_show_detail_screen/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            SlideShowDetailScreen(id,navController)
        }

        composable("CategoryScreen") {
            CategoryScreen(navController)
        }
        composable(
            "category_detail_screen/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            CategoryDetailScreen(id,navController)
        }

        composable("CamelCaseScreen") {
            CamelCaseScreen(navController)
        }
        composable(
            "camelcase_detail_screen/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            CamelCaseDetailScreen(id,navController)
        }

        composable("CustomerScreen") {
            CustomerScreen(navController)
        }
        composable(
            "customer_detail_screen/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            CustomerDetailScreen(id,navController)
        }

        composable("EmployeeScreen") {
            EmployeeScreen(navController)
        }
        composable(
            "employee_detail_screen/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            EmployeeDetailScreen(id,navController)
        }

        composable("OrderScreen") {
            OrderScreen(navController)
        }
        composable(
            "order_detail_screen/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            OrderDetailScreen(id,navController)
        }

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
