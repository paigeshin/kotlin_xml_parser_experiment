package com.paigesoftware.xmlretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.paigesoftware.xmlretrofit.model.Channel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RetrofitClient.instance.fetchKoreanTranslation("BE6CE91A004B80A221AC34965EE90F3E" , word = "안녕", translated = "y", trans_lang = 3).enqueue(object: Callback<Channel>{
            override fun onResponse(call: Call<Channel>, response: Response<Channel>) {
                Toast.makeText(this@MainActivity, "Success!", Toast.LENGTH_LONG).show()
//                Toast.makeText(this@MainActivity, response., Toast.LENGTH_LONG).show()
                println("data::: ${response.body()?.itemList?.get(0) ?: "none"}")
            }

            override fun onFailure(call: Call<Channel>, t: Throwable) {
                Toast.makeText(this@MainActivity, "failed!", Toast.LENGTH_LONG).show()
                Log.d("Err::: ", t.message.toString())
            }

        })


//        RetrofitClient.instance.fetchKoreanTranslation("BE6CE91A004B80A221AC34965EE90F3E" , word = "안녕", translated = "y", trans_lang = 3).enqueue(object: Callback<Channel>{
//            override fun onResponse(call: Call<Channel>, response: Response<Channel>) {
//                Toast.makeText(this@MainActivity, "Success!", Toast.LENGTH_LONG).show()
//                val channel = Channel()
////                val title = channel.items!![0].word
//                println("data::: ${channel.title.toString()}")
////                Toast.makeText(this@MainActivity, channel.itemList!![0].word, Toast.LENGTH_LONG).show()
////                println("channel: ${channel.title}")
////                Toast.makeText(this@MainActivity, channel.title, Toast.LENGTH_LONG).show()
//            }
//
//            override fun onFailure(call: Call<Channel>, t: Throwable) {
//                Toast.makeText(this@MainActivity, "failed!", Toast.LENGTH_LONG).show()
//                Log.d("Err::: ", t.message.toString())
//            }
//
//        })
//            RetrofitClient.instance.fetchKoreanTranslation("BE6CE91A004B80A221AC34965EE90F3E" , word = "안녕", translated = "y", trans_lang = 3)

    }
}