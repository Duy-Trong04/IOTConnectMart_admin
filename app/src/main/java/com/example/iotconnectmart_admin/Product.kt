package com.example.iotconnectmart_admin

// SlideShow
data class ProductSlideShow(
    var id: Int,
    var slideTitle: String,
    var label: String,
    var targetUrl: String,
    var postedDate: String,
    var endDate: String,
    var shortContent: String,
    var state: String,
    var image: String
)

// Danh mục
data class ProductCategory(
    var idC: Int,
    var categoryId: String,
    var categoryName: String,
    var createdBy: String,
    var parentCategory: String,
    var creationDate: String,
    var categoryDescription: String,
    var state: String,
    var image: String
)

// Sản Phẩm
data class CamelCase(
    var productId: Int,
    var productName: String,
    var productCategory: String,
    var costPrice: Double,
    var sellingPrice: Double,
    var soldQuantity: Int,
    var stockQuantity: Int,
    var productDescription: String,
    var technicalSpecifications: String,
    var state: String,
    var image: String
)

// Khách hàng
data class Customer(
    var id:Int,
    var CustomerId: String,
    var User: String,
    var Password: String,
    var Name: String,
    var CCCD: String,
    var Phone: Int,
    var Address: String,
    var DateOfBirth: String,
    var Set: String,
    var taxCode: String,
    var Email: String,
    var RegistrationDate: String,
    var state: Boolean,
    var image: String
)

// Nhân viên
data class Employee(
    var id:Int,
    var employeeId: String,
    var User: String,
    var Password: String,
    var Name: String,
    var CCCD: String,
    var Phone: Int,
    var Address: String,
    var DateOfBirth: String,
    var Set: String,
    var Position: String,
    var Email: String,
    var RegistrationDate: String,
    var state: Boolean,
    var image: String
)

// Đơn hàng
data class Order(
    var id:Int,
    var orderId: String,
    var CustomerName: String,
    var CustomerPhone: Int,
    var CustomerAddress: String,
    var CustomerEmail: String,
    var EmployeeName: String,
    var OrderDate: String,
    var Notes: String,
    var state: String
)

// Sản phẩm trong hóa đơn
data class OrderCamelCase(
    var id:Int,
    var productId: Int,
    var productName: String,
    var sellingPrice: Double,
    var quantity: Int,
    var image: String
)