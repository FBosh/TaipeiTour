package taipei.travel.taipeitour.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import taipei.travel.taipeitour.Constants
import java.util.concurrent.TimeUnit

object RetrofitSingleton {
    private val client = OkHttpClient.Builder().apply {
        addInterceptor {
            it.proceed(
                    it.request().newBuilder().apply {
                        addHeader("accept", "application/json")
                    }.build()
            )
        }

        callTimeout(30, TimeUnit.SECONDS)
    }.build()

    private val factory = GsonBuilder().setDateFormat("YYYY-MM-DD hh:mm:ss")

    private val retrofit = Retrofit.Builder().apply {
        baseUrl("https://${Constants.BASE_URL}/")
        client(client)
        addConverterFactory(GsonConverterFactory.create(factory.create()))
    }.build()

    val attractionService: AttractionService = retrofit.create(AttractionService::class.java)
}

//region <Former codes>

//class APIAdapter {
////    val retrofit = Retrofit.Builder().build()
//
//    fun testHTTP(path: String): Pair<Boolean, String> {
//        val url: URL
//        var resultText = ""
////        var result: Boolean
//        var result = Pair(false, resultText)
//
//        try {
//            url = URL("https://$path")
//
//        } catch (e: MalformedURLException) {
//            e.printStackTrace()
//            resultText = e::class.java.toString()
//            printBoshLog(resultText)
//
//            return Pair(false, resultText)
//        }
//
//        (url.openConnection() as HttpsURLConnection).apply {
//            printBoshLog("connection= $this")
//
////            setRequestProperty("accept", "application/json")
//
//            Thread {
//                try {
//                    connect()
//
//                    printBoshLog("content= $content")
//
//                    inputStream.bufferedReader().apply {
//                        resultText = readText()
//                        printBoshLog("resultText= $resultText")
//
//                        close()
//                    }
//
//                    disconnect()
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                    resultText = e::class.java.toString()
//                    printBoshLog(resultText)
//
//                    result = Pair(false, resultText)
//                }
//            }.start()
//        }
//
//        return result
//    }
//
//    private fun printBoshLog(msg: String) = Utils.printBoshLog(msg)
//}

//endregion
