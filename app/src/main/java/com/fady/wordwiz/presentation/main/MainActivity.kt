package com.fady.wordwiz.presentation.main

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.fady.wordwiz.data.datasource.local.sharedpref.LocalDataSource
import com.fady.wordwiz.presentation.navgraph.AppGraph
import com.fady.wordwiz.presentation.navgraph.Route
import com.fady.wordwiz.presentation.theme.wordWizTheme
import com.fady.wordwiz.presentation.utils.NetworkStateReceiver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var requestSplashRemoved: Boolean? = null
    private lateinit var networkStateReceiver: NetworkStateReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            requestSplashRemoved == null
        }
        networkStateReceiver = NetworkStateReceiver { isConnected ->
            LocalDataSource.isConnected = isConnected
        }

        setContent {
            val navController = rememberNavController()
            wordWizTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    AppGraph(startDestination = Route.HomeScreen,
                        navController = navController,
                        paddingValues = it,
                        onRequestSplashRemoved = {
                            requestSplashRemoved = true
                        })
                }

            }
        }
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkStateReceiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(networkStateReceiver)
    }


}
