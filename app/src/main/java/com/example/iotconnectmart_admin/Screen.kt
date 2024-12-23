package com.example.iotconnectmart_admin

sealed class Screen(var route: String) {

    object SlideShowScreen : Screen("SlideShowScreen")
    object SlideShowDetailScreen : Screen("slide_show_detail_screen/{id}") {
        fun createRoute(id: Int) = "slide_show_detail_screen/$id"
    }

    object CategoryScreen : Screen("CategoryScreen")
    object CategoryDetailScreen : Screen("category_detail_screen/{id}") {
        fun createRoute(id: Int) = "category_detail_screen/$id"
    }

    object CamelCaseScreen : Screen("CamelCaseScreen")
    object CamelCaseDetailScreen : Screen("camelcase_detail_screen/{id}") {
        fun createRoute(id: Int) = "camelcase_detail_screen/$id"
    }

    object CustomerScreen : Screen("CustomerScreen")
    object CustomerDetailScreen : Screen("customer_detail_screen/{id}") {
        fun createRoute(id: Int) = "customer_detail_screen/$id"
    }

    object EmployeeScreen : Screen("EmployeeScreen")
    object EmployeeDetailScreen : Screen("employee_detail_screen/{id}") {
        fun createRoute(id: Int) = "employee_detail_screen/$id"
    }

    object OrderScreen : Screen("OrderScreen")
    object OrderDetailScreen : Screen("order_detail_screen/{id}") {
        fun createRoute(id: Int) = "order_detail_screen/$id"
    }
}