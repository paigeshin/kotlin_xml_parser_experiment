package com.paigesoftware.xmlretrofit

import com.paigesoftware.xmlretrofit.model.Channel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    @GET("/api/search")
    fun fetchKoreanTranslation(
        @Query("key") apiKey: String,
        @Query("q") word: String,
        @Query("translated") translated: String,
        @Query("trans_lang") trans_lang: Int
    ): Call<Channel>

//    @GET("/api/search")
//    fun fetchKoreanTranslation(
//        @Query("key") apiKey: String = "BE6CE91A004B80A221AC34965EE90F3E",
//        @Query("q") word: String
//    ): Response<Channel>
//    https://krdict.korean.go.kr/api/search?key=BE6CE91A004B80A221AC34965EE90F3E&q=보지&translated=y&trans_lang=3
}

