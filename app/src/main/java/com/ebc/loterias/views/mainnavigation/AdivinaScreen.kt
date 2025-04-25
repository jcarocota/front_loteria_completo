package com.ebc.loterias.views.mainnavigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ebc.loterias.viewmodel.AdivinaViewModel

@Composable
fun AdivinaScreen(viewModel: AdivinaViewModel) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = viewModel.inputNumero.value,
            onValueChange = { viewModel.inputNumero.value = it },
            label = { Text("Ingresa un número") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        viewModel.errorMessage.value?.let {
            Text(text = it, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
        }
        Button (onClick = { viewModel.verificar() }, modifier = Modifier.padding(top = 16.dp)) {
            Text(text = "Verificar")
        }
        if (viewModel.isLoading.value) {
            CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
        }
        viewModel.resultado.value?.let {
            Text(text = it, modifier = Modifier.padding(top = 16.dp))
        }
    }
}