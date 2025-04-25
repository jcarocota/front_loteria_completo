package com.ebc.loterias.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebc.loterias.network.ApiClient
import com.ebc.loterias.network.JuegoService
import kotlinx.coroutines.launch

class AdivinaViewModel : ViewModel() {
    private val service = ApiClient.retrofit.create(JuegoService::class.java)

    val isLoading = mutableStateOf(false)
    val inputNumero = mutableStateOf("")
    val resultado = mutableStateOf<String?>(null)
    val errorMessage = mutableStateOf<String?>(null)

    fun verificar() {
        val numero = inputNumero.value.toIntOrNull()
        if (numero == null) {
            errorMessage.value = "Ingresa un número válido"
            return
        }
        viewModelScope.launch {
            isLoading.value = true
            errorMessage.value = null
            try {
                val res = service.verificarNumero(numero)
                resultado.value = res
            } catch (e: Exception) {
                errorMessage.value = e.message ?: "Error en el servidor"
            } finally {
                isLoading.value = false
            }
        }
    }
}