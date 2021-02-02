package com.paigesoftware.xmlparser

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.StandardCharsets

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            setData("https://krdict.korean.go.kr/api/search?key=BE6CE91A004B80A221AC34965EE90F3E&q=피자&translated=n&trans_lang=2")
            // https://krdict.korean.go.kr/api/search?key=BE6CE91A004B80A221AC34965EE90F3E&q=query&advanced=y&trans_lang=3
        }


    }

    suspend fun setDataWithString(url: String?) {

        //item, word, origin, link
        //definition, translation
        //trans_lang, trans_word, trans_dfn

        var br: BufferedReader? = null
        try {
            val conn: HttpURLConnection = URL(url).openConnection() as HttpURLConnection
            conn.requestMethod = "GET"

            br = BufferedReader(InputStreamReader(conn.inputStream))
            var line: String?
            val sb = StringBuilder()
            while (br.readLine().also { line = it } != null) {
                withContext(Dispatchers.Main) {

                    if(line!!.contains("<word>")) {
                        editText.append("word: " + line + "\n")
                    } else if(line!!.contains("<origin>")){
                        editText.append("origin: " + line + "\n")
                    } else if(line!!.contains("<link>" + "\n")) {
                        editText.append("link: " + line)
                    } else if(line!!.contains("definition")) {
                        editText.append("definition: " + line + "\n")
                    } else if(line!!.contains("translation")) {
                        editText.append("translation: " + line + "\n")
                    } else if(line!!.contains("trans_lang")) {
                        editText.append("trans_lang: " + line + "\n")
                    } else if(line!!.contains("trans_word")) {
                        editText.append("trans_word: " + line + "\n")
                    } else if(line!!.contains("trans_dfn")) {
                        editText.append("trans_dfn: " + line + "\n")
                    }

                }

            }
            sb.toString()
            println("data string:::: ${sb.toString()}")



        } catch (e: java.lang.Exception) {

        }

    }

    suspend fun setData(url: String?) {
        var br: BufferedReader? = null
        try {
            val conn: HttpURLConnection = URL(url).openConnection() as HttpURLConnection

            br = BufferedReader(InputStreamReader(conn.inputStream))
            var line: String?
            val sb = StringBuilder()
            while (br.readLine().also { line = it } != null) {
                sb.append(line).append("\n")
            }
            sb.toString()
            println("data string:::: ${sb.toString()}")

            val xmlPullParserFactory = XmlPullParserFactory.newInstance()
            val xmlPullParser = xmlPullParserFactory.newPullParser()
            xmlPullParser.setInput(StringReader(sb.toString().trim()))
            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            var event: Int = xmlPullParser.eventType
            while(event != XmlPullParser.END_DOCUMENT) {
                when(event) {

                    //item, word, origin, link
                    //definition, translation
                    //trans_lang, trans_word, trans_dfn




                    XmlPullParser.START_TAG -> {

                        println("name::: ${xmlPullParser.name}")

//                        if (xmlPullParser.name == "title") {
//                            val name = "title: " + xmlPullParser.nextText()
//                            withContext(Dispatchers.Main) {
//                                editText.append(name.trim() + "\n\n\n")
//                            }
//                        }

                        if (xmlPullParser.name == "word"){
                            val name = "word: " + xmlPullParser.nextText()
                            withContext(Dispatchers.Main) {
                                editText.append(name.trim() + "\n")
                            }
                        }

                        if (xmlPullParser.name == "origin"){
                            val name = "origin: " + xmlPullParser.nextText()
                            withContext(Dispatchers.Main) {
                                editText.append(name.trim() + "\n")
                            }
                        }

                        if (xmlPullParser.name == "pronunciation"){
                            val name = "pronunciation: " + xmlPullParser.nextText() + "\n"
                            withContext(Dispatchers.Main) {
                                editText.append(name.trim() + "\n")
                            }
                        }

                        //array
                        if (xmlPullParser.name == "definition"){
                            val name = "definition: " + xmlPullParser.nextText() + "\n"
                            withContext(Dispatchers.Main) {
                                editText.append(name.trim() + "\n")
                            }
                        }

//                        if (xmlPullParser.name == "trans_lang"){
//                            val name = "trans_lang: " + xmlPullParser.nextText()
//                            withContext(Dispatchers.Main) {
//                                editText.append(name.trim() + "\n")
//                                Toast.makeText(this@MainActivity, name, Toast.LENGTH_LONG).show()
//                            }
//                        }

                        if (xmlPullParser.name == "trans_word"){
                            val name = "trans_word: " + xmlPullParser.nextText()
                            withContext(Dispatchers.Main) {
                                editText.append(name.trim() + "\n")
                            }
                        }

                        if (xmlPullParser.name == "trans_dfn"){
                            val name = "trans_dfn: " + xmlPullParser.nextText()
                            withContext(Dispatchers.Main) {
                                editText.append(name.trim() + "\n")
                            }
                        }

                    }
                }
                event = xmlPullParser.next()
            }

        } catch (e: java.lang.Exception) {

        }

    }

    fun getXMLFromUrl(url: String?): String? {
        var br: BufferedReader? = null
        return try {
            val conn: HttpURLConnection = URL(url).openConnection() as HttpURLConnection
            br = BufferedReader(InputStreamReader(conn.inputStream))
            var line: String?
            val sb = StringBuilder()
            while (br.readLine().also { line = it } != null) {
                sb.append(line).append("\n")
            }
            sb.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        } finally {
            try {
                br?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }


    fun parseLocalXML() {
        val xmlData: InputStream = assets.open("demo.xml")
        val xmlPullParserFactory = XmlPullParserFactory.newInstance()
        val xmlPullParser = xmlPullParserFactory.newPullParser()
        xmlPullParser.setInput(xmlData, "UTF-8")

        var event: Int = xmlPullParser.eventType
        while(event != XmlPullParser.END_DOCUMENT) {
            val tagName = xmlPullParser.name
            when(event) {
                XmlPullParser.END_TAG -> {
                    if (tagName == "student") {
                        val name = xmlPullParser.getAttributeValue(0) + " " + xmlPullParser.getAttributeValue(1) + "\n"
                        editText.append(name)
                    }
                    if (tagName == "teacher") {
                        val name = xmlPullParser.getAttributeValue(0) + " " + xmlPullParser.getAttributeValue(1) + "\n"
                        editText.append(name)
                    }
                }
                XmlPullParser.START_TAG -> {

                    if (xmlPullParser.name == "title") {
                        val name = "fucking xml " + xmlPullParser.nextText()
                        editText.append(name)
                    }

                }
            }
            event = xmlPullParser.next()
        }
    }

}


