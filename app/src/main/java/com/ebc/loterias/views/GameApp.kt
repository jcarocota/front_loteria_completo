package com.ebc.loterias.views

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ebc.loterias.viewmodel.AdivinaViewModel
import com.ebc.loterias.viewmodel.LoteriaViewModel
import com.ebc.loterias.views.mainnavigation.AdivinaScreen
import com.ebc.loterias.views.mainnavigation.LoteriaScreen
import com.ebc.loterias.views.mainnavigation.MainScreen

@Composable
fun GameApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainScreen(navController) }
        composable("loteria") { LoteriaScreen(viewModel = LoteriaViewModel()) }
        composable("adivina") { AdivinaScreen(viewModel = AdivinaViewModel()) }
    }
}