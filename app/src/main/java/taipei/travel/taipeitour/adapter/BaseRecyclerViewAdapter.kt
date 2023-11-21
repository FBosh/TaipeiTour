package taipei.travel.taipeitour.adapter

import androidx.recyclerview.widget.RecyclerView
import taipei.travel.taipeitour.util.Utils

abstract class BaseRecyclerViewAdapter<ViewHolder : RecyclerView.ViewHolder> : RecyclerView.Adapter<ViewHolder>() {
    protected fun printBoshLog(msg: String) = Utils.printBoshLog(msg)
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
