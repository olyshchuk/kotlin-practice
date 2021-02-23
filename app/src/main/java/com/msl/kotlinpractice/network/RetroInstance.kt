package com.msl.kotlinpractice.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {

    companion object {
        private const val outUrl = "https://jsonplaceholder.typicode.com"

        fun getRetroInstance() : Retrofit {

            return Retrofit.Builder()
                    .baseUrl(outUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }
    }
}