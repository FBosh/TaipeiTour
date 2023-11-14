package taipei.travel.taipeitour.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import taipei.travel.taipeitour.Constants

object RetrofitSingleton {
    private val client = OkHttpClient.Builder().apply {
        addInterceptor {
            it.proceed(
                    it.request().newBuilder().apply {
                        addHeader("accept", "application/json")
                    }.build()
            )
        }
    }.build()

    private val factory = GsonBuilder().setDateFormat("YYYY-MM-DD hh:mm:ss")

    private val retrofit = Retrofit.Builder().apply {
        baseUrl("https://${Constants.BASE_URL}/")
        client(client)
        addConverterFactory(GsonConverterFactory.create(factory.create()))
    }.build()

    val attractionService: AttractionService = retrofit.create(AttractionService::class.java)
}
