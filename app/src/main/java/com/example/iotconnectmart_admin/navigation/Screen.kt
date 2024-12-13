package com.example.iotconnectmart_admin.navigation

sealed class Screen(val route: String) {
    object ImportProductList : Screen("ImportProductListScreen")
    object ImportProductDetail : Screen("ImportProductDetailScreen/{productId}") {
        fun createRoute(importProductID: String) = "ImportProductDetailScreen/$importProductID"
    }
    object AttributeListScreen: Screen("AttributeListScreen")
    object AttributeDetailScreen: Screen("AttributeDetailScreen")
    object AttributeGroupScreen: Screen("AttributeGroupScreen")
    object StatisticsScreen: Screen("StatisticsScreen")
    object DashboardScreen: Screen("DashboardScreen")
}
