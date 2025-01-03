package com.example.iotconnectmart_admin.screen.ManageAttributes.detailGroupAttribute

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController







/** Man hình  Chi tiết nhóm thuộc tính
 * -------------------------------------------
 * Người code: Nguyễn Mạnh Cường
 * Ngày viết: 18/12/2024
 * Lần cập nhật cuối cùng: 18/12/2024
 * -------------------------------------------
 * Input: chưa có
 *
 * Output: Chứa các thành phần đối tượng màn hình
 * ------------------------------------------------------------
 * Người cập nhật:
 * Ngày cập nhật:
 * ------------------------------------------------------------
 * Nội dung cập nhật:
 *
 */
@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true)
@Composable
fun AttributeGroupScreen(navController:NavHostController) {
    //du lieu mau
    var id by remember { mutableStateOf("TT001") }
    var name by remember { mutableStateOf("") }
    var status by remember { mutableStateOf(true) }
    var expandedStatus by remember { mutableStateOf(false) }

    val statusOptions = listOf(true to "Đang hoạt động", false to "Không hoạt động")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chi tiết nhóm thuộc tính") },
                navigationIcon = {
                    IconButton(onClick = { /* Xử lý quay lại */navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Quay lại", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF5F9EFF),
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                //containerColor = Color(0xFF5F9EFF),
                //modifier = Modifier.height(46.dp),
                content = {
                    //Nut luu thong tin
                    Button(
                        onClick = { /* Chuc nang luu thong tin */ },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF5F9EFF)),
                        //enabled = name.isNotEmpty(),
                    ) {
                        Text("Lưu", color = Color.White)
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("ID: $id", style = MaterialTheme.typography.titleMedium)

                // Attribute Name Input
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Tên thuộc tính") },
                    modifier = Modifier.fillMaxWidth(),
                )

                //  Dropdown Trang thai
                ExposedDropdownMenuBox(
                    expanded = expandedStatus,
                    onExpandedChange = { expandedStatus = !expandedStatus }
                ) {
                    OutlinedTextField(
                        value = if (status) "Đang hoạt động" else "Không hoạt động",
                        onValueChange = {},
                        label = { Text("Trạng thái") },
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedStatus) },
                        modifier = Modifier.fillMaxWidth().menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expandedStatus,
                        onDismissRequest = { expandedStatus = false }
                    ) {
                        statusOptions.forEach { (value, label) ->
                            DropdownMenuItem(
                                text = { Text(label) },
                                onClick = {
                                    status = value
                                    expandedStatus = false
                                }
                            )
                        }
                    }
                }
            }
        }
    )
}
