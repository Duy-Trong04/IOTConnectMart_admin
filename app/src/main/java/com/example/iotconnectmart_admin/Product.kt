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
    var id: String,
    var surname: String,
    var lastName: String,
    var phone: String,
    var email: String,
    var birthdate: String,
    var gender: Int,
    var created_at: String,
    var updated_at: String,
    var status: Int
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
    var id: Int,
    var idCustomer: String,
    var totalAmount: String,
    var paymentMethod: String,
    var address: String,
    var accountNumber: String,
    var phone: String,
    var nameRecipient: String,
    var note: String?,
    var platformOrder: String,
    var created_at: String,
    var updated_at: String,
    var accept_at: String,
    var idEmployee: String,
    var status: Int
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