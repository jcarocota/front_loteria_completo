package com.ebc.loterias.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ebc.loterias.viewmodel.AdivinaViewModel
import com.ebc.loterias.viewmodel.LoteriaViewModel
import com.ebc.loterias.views.mainnavigation.AdivinaScreen
import com.ebc.loterias.views.mainnavigation.LoteriaScreen
import com.ebc.loterias.views.mainnavigation.MainScreen
import kotlinx.coroutines.launch

@Composable
fun GameApp() {
    val navController = rememberNavController()

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        scaffoldState = scaffoldState,
        drawerContent = {
            Column (modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                TextButton (onClick = { navController.navigate("loteria"); scope.launch { scaffoldState.drawerState.close() } }) {
                    Text("Lotería")
                }
                TextButton(onClick = { navController.navigate("adivina"); scope.launch { scaffoldState.drawerState.close() } }) {
                    Text("Adivina el número")
                }
            }
        },
        topBar = {
            val backStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = backStackEntry?.destination?.route
            val canNavigateBack = currentRoute != "main"

            // Define aquí los títulos según la ruta
            val appBarTitle = when (currentRoute) {
                "loteria" -> "Lotería"
                "adivina" -> "Adivina el número"
                else      -> "Loterías y más"  // para "main" y cualquier otra
            }

            TopAppBar (
                title = { Text(appBarTitle) },
                navigationIcon = {
                    IconButton (
                        onClick = {
                            if (canNavigateBack) navController.popBackStack()
                            else scope.launch { scaffoldState.drawerState.open() }
                        }
                    ) {
                        Icon(
                            imageVector = if (canNavigateBack) Icons.AutoMirrored.Filled.ArrowBack else Icons.Filled.Menu,
                            contentDescription = if (canNavigateBack) "Atrás" else "Menú"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = "main",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("main") { MainScreen(navController) }
            composable("loteria") { LoteriaScreen(viewModel = LoteriaViewModel()) }
            composable("adivina") { AdivinaScreen(viewModel = AdivinaViewModel()) }
        }
    }
}