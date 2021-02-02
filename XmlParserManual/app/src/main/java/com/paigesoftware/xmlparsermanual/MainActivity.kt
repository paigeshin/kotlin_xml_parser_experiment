package com.paigesoftware.xmlparsermanual

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var xml: String? = null
        val data = ArrayList<String>()

        try {
            val address = "https://krdict.korean.go.kr/api/search?key=BE6CE91A004B80A221AC34965EE90F3E&q=보지&translated=y&trans_lang=3"
            val url = URL(address)
            val httpURLConnection: HttpURLConnection? = url.openConnection() as? HttpURLConnection
            httpURLConnection?.connectTimeout = 10000
            httpURLConnection?.useCaches = false

            val bufferedReader: BufferedReader = BufferedReader(InputStreamReader(httpURLConnection?.inputStream))
            val stringBuilder = StringBuilder()
            while(true) {
                val line = bufferedReader.readLine() ?: break
                stringBuilder.append(line)
            }
            xml = stringBuilder.toString()
            println("xml::: $xml")

        } catch (error: Exception) {
            println("네트워크 에러" + error.message)
        }

        try {
            // 자신의 static 메서드를 가지고 객체를 생성 : 싱글톤 패턴
            val factory = DocumentBuilderFactory.newInstance()
            // 다른 클래스의 객체를 가지고, 객체를 생성하면 팩토리 패턴.
            val documentbuilder: DocumentBuilder = factory.newDocumentBuilder() //// 팩토리 메서드 패턴  공장에서 찍어줌
            // 문자열을 InputStream으로 변환
            val `is`: InputStream = ByteArrayInputStream(xml!!.toByteArray())
            val doc: Document = documentbuilder.parse(`is`)
            // xml을 메모리에 펼쳐놓고 루트를 elemnt에 저장
            val element: Element = doc.getDocumentElement()

            // 파싱할 태그의 리스트를 찾아온다
            // tmx 태그 전체를 list에 저장
            val list: NodeList = element.getElementsByTagName("tmx")
            // 리스트를 순회하면서 데이터를 data에 추가
            for (i in 0 until list.getLength()) {
                // i번째 tmx 태그를 node에 저장
                val node: Node = list.item(i)
                // 태그 내의 첫번째 값 앞으로 이동
                val temp: Node = node.getFirstChild()
                // 태그 내의 첫번째 값을 value에 저장
                val value: String = temp.getNodeValue()
                // 값을 data에 저장
                data.add(value)
            }
        } catch (e: Exception) {
            println("파싱에러" + e.message)
        }

        // data의 내용을 출력 - 단순 확인만 하는 경우
        // 컬렉션의 toString은 각 데이터의 toString을 다시 호출

        // data의 내용을 출력 - 단순 확인만 하는 경우
        // 컬렉션의 toString은 각 데이터의 toString을 다시 호출
        println(data)

        for (imsi in data) {
            println(imsi)
        }

    }
}