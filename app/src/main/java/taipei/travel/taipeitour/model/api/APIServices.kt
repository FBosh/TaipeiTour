package taipei.travel.taipeitour.model.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import taipei.travel.taipeitour.model.api.data.*
import taipei.travel.taipeitour.model.data.APIResponse

interface AttractionService {
    @GET("{lang}/Attractions/All")
    fun getAttractions(@Path("lang") language: String): Call<APIResponse<List<Attraction>>>
}

interface EventService {
    @GET("{lang}/Events/News")
    fun getNews(@Path("lang") language: String): Call<APIResponse<List<News>>>

    @GET("{lang}/Events/Activity")
    fun getActivities(@Path("lang") language: String): Call<APIResponse<List<Activity>>>

    @GET("{lang}/Events/Calendar")
    fun getCalendars(@Path("lang") language: String): Call<APIResponse<List<Calendar>>>
}

interface MediumService {
    @GET("{lang}/Media/Panos")
    fun getPanos(@Path("lang") language: String): Call<APIResponse<List<Pano>>>

    @GET("{lang}/Media/Audio")
    fun getAudio(@Path("lang") language: String): Call<APIResponse<List<Audio>>>
}

interface MiscellanyService {
    @GET("{lang}/Miscellaneous/Categories")
    fun getCategories(@Path("lang") language: String): Call<APIResponse<List<Category>>>
}

interface TourService {
    @GET("{lang}/Tours/Theme")
    fun getThemes(@Path("lang") language: String): Call<APIResponse<List<Tour>>>
}
