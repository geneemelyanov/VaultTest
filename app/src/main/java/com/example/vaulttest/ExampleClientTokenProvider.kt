package com.example.vaulttest

import com.braintreepayments.api.ClientTokenCallback
import com.braintreepayments.api.ClientTokenProvider
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


internal class ExampleClientTokenProvider : ClientTokenProvider {
    override fun getClientToken(callback: ClientTokenCallback) {
        val call: Call<ClientToken> = createService().getClientToken()
        call.enqueue(object : Callback<ClientToken?> {
            override fun onResponse(call: Call<ClientToken?>?, response: Response<ClientToken?>?) {
                response?.body()?.value?.let { callback.onSuccess(it) }
            }

            override fun onFailure(call: Call<ClientToken?>?, t: Throwable?) {
                callback.onFailure(Exception(t))
            }
        })
    }

    companion object {
        private val builder = Retrofit.Builder()
            .baseUrl("https://my-api.com")
            .addConverterFactory(GsonConverterFactory.create())
        private val httpClient = OkHttpClient.Builder()
        fun createService(): Api {
            builder.client(httpClient.build())
            val retrofit = builder.build()
            return retrofit.create(Api::class.java)
        }
    }
}