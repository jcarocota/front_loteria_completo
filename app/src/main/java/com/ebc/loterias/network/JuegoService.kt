package com.ebc.loterias.network

import retrofit2.http.GET
import retrofit2.http.Query

interface JuegoService {
    @GET("loteria")
    suspend fun generarNumeros(): List<Int>

    @GET("adivina")
    suspend fun verificarNumero(@Query("intento") intento: Int): String
}