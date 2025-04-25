package com.ebc.loterias.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebc.loterias.network.ApiClient
import com.ebc.loterias.network.JuegoService
import kotlinx.coroutines.launch

class LoteriaViewModel : ViewModel() {
    private val service = ApiClient.retrofit.create(JuegoService::class.java)

    val isLoading = mutableStateOf(false)
    val numeros = mutableStateListOf<Int>()
    val errorMessage = mutableStateOf<String?>(null)

    fun generarNumeros() {
        viewModelScope.launch {
            isLoading.value = true
            errorMessage.value = null
            try {
                val result = service.generarNumeros()
                numeros.clear()
                numeros.addAll(result)
            } catch (e: Exception) {
                errorMessage.value = e.message ?: "Error inesperado"
            } finally {
                isLoading.value = false
            }
        }
    }
}