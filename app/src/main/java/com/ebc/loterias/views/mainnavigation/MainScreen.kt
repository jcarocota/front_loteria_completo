package com.ebc.loterias.views.mainnavigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ebc.loterias.R
import com.ebc.loterias.components.GameOption

@Composable
fun MainScreen(navController: NavHostController) {

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Loterías y más",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp, top = 19.dp)
                .align(Alignment.CenterHorizontally),

        )
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            GameOption(
                lottieRes = R.raw.lottie_loteria,
                label = stringResource(R.string.loteria_label)
            ) { navController.navigate("loteria") }

            GameOption(
                lottieRes = R.raw.lottie_adivina,
                label = stringResource(R.string.adivina_label)
            ) { navController.navigate("adivina") }
        }
    }
}