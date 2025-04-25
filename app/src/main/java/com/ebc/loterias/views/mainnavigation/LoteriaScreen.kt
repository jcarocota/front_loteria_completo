package com.ebc.loterias.views.mainnavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ebc.loterias.viewmodel.LoteriaViewModel

@Composable
fun LoteriaScreen(viewModel: LoteriaViewModel) {
    val redShades = listOf(
        Color(0xFFB71C1C), // más oscuro
        Color(0xFFC62828),
        Color(0xFFD32F2F),
        Color(0xFFE53935),
        Color(0xFFEF5350),
        Color(0xFFF44336)  // ligeramente más claro
    );

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (viewModel.isLoading.value) {
            CircularProgressIndicator()
        } else {
            Button (onClick = { viewModel.generarNumeros() }) {
                Text(text = "Generar Números")
            }
            viewModel.errorMessage.value?.let { error ->
                Text(text = error, color = androidx.compose.ui.graphics.Color.Red)
            }
            if (viewModel.numeros.isNotEmpty()) {
                LazyRow (
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(top = 16.dp)
                ) {

                    itemsIndexed(viewModel.numeros) { index, num ->
                        //val bgColor = redShades.getOrElse(index) { Color.Red }
                        val bgColor = redShades.random()
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(48.dp)
                                .background(color = bgColor, shape = CircleShape)
                        ) {
                            Text(text = num.toString(), color = Color.White)
                        }
                    }
                }
            }
        }
    }
}
