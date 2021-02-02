package com.paigesoftware.xmlretrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory


object RetrofitClient {

    private const val API_KEY = "BE6CE91A004B80A221AC34965EE90F3E"
    private const val BASE_URL = "https://krdict.korean.go.kr/"


    private val interceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)
    }.build()
            /** TimeOut **/
//            .connectTimeout(30, TimeUnit.SECONDS)
//            .readTimeout(20, TimeUnit.SECONDS)
//            .writeTimeout(25, TimeUnit.SECONDS)


    val instance: API by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
            retrofit.create(API::class.java)
    }

}