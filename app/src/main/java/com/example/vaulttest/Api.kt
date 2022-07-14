package com.example.vaulttest

import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("/client_token")
    fun getClientToken(): Call<ClientToken>
}