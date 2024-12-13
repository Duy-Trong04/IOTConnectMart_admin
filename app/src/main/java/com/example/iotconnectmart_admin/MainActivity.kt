package com.example.iotconnectmart_admin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.iotconnectmart_admin.navigation.NavGraph
//import com.example.iotconnectmart_admin.screen.ManageAttributes.attributeList.AttributeScreen
import com.example.iotconnectmart_admin.ui.theme.IOTConnectMart_adminTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IOTConnectMart_adminTheme {
                val navController = rememberNavController()
               NavGraph(navController = navController)
                //AttributeScreen()
            }
        }
    }
}

