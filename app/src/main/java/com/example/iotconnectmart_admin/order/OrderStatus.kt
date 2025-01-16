package com.example.iotconnectmart_admin.order

enum class OrderStatus(var status: Int, var displayName: String) {
    CHO_VAN_XAC_NHAN(1, "Chờ vận xác nhận"),
    CHO_LAY_HANG(2, "Chờ lấy hàng"),
    CHO_GIAO_HANG(3, "Chờ lấy giao hàng"),
    DA_GIAO_HANG(4, "Đã giao hàng"),
    DA_HUY(5, "Đã hủy")
}



enum class statusOptions(var status: Int, var displayName: String) {
    TAT_CA(0, "Tất cả"),
    CHO_VAN_XAC_NHAN(1, "Chờ vận xác nhận"),
    CHO_LAY_HANG(2, "Chờ lấy hàng"),
    CHO_GIAO_HANG(3, "Chờ lấy giao hàng"),
    DA_GIAO_HANG(4, "Đã giao hàng"),
    DA_HUY(5, "Đã hủy")
}