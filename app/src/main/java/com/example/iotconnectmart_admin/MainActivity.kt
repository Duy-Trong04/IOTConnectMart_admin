package com.example.iotconnectmart_admin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.iotconnectmart_admin.customer.CustomerViewModel
import com.example.iotconnectmart_admin.order.OrderViewModel
import com.example.iotconnectmart_admin.ui.theme.IOTConnectMart_adminTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val orderViewModel by viewModels<OrderViewModel>()
        val customerViewModel by viewModels<CustomerViewModel>()
        lateinit var navController: NavHostController

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IOTConnectMart_adminTheme {
                navController = rememberNavController()
                NavGraph(navController,orderViewModel,customerViewModel)
            }
        }
    }
}
