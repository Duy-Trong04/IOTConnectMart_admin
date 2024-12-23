package com.example.iotconnectmart_admin

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "OrderScreen"
    ) {
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

    }
}
