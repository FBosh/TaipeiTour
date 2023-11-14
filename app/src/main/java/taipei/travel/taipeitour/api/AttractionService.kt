package taipei.travel.taipeitour.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import taipei.travel.taipeitour.model.APIResponse
import taipei.travel.taipeitour.model.Attraction

interface AttractionService {
    @GET("{lang}/Attractions/All")
    fun getAttractions(@Path("lang") language: String): Call<APIResponse<List<Attraction>>>
}
