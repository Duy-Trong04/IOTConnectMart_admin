package com.example.iotconnectmart_admin.data

data class Attribute(
    val id: String,                     // ID thuộc tính
    val name: String,                   // Tên thuộc tính
    val category: String,               // Danh mục thuộc tính
    val group: String,                  // Nhóm thuộc tính
    val dataType: String,               // Kiểu dữ liệu (ví dụ: Text, Number, Date)
    val isRequired: Boolean,            // Bắt buộc nhập (true/false)
    val status: String                 // Trạng thái (Đang hoạt động hoặc Không hoạt động)
)
