package taipei.travel.taipeitour.viewmodel

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import taipei.travel.taipeitour.base.BaseViewModel
import taipei.travel.taipeitour.model.api.RetrofitSingleton
import taipei.travel.taipeitour.model.api.data.Attraction
import taipei.travel.taipeitour.model.api.data.News
import taipei.travel.taipeitour.model.data.APIResponse
import taipei.travel.taipeitour.util.SelectedLanguage

class MainViewModel : BaseViewModel() {
    var language = SelectedLanguage.ZH_TW
    var msgThrowable = ""; private set

    val stateNews = MutableLiveData<RetrofitSingleton.State>().apply {
        value = RetrofitSingleton.State.NONE
    }

    val stateAttraction = MutableLiveData<RetrofitSingleton.State>().apply {
        value = RetrofitSingleton.State.NONE
    }

    private val news = arrayListOf<News>()
    private val attractions = arrayListOf<Attraction>()

    fun getNews() = news.toList()

    fun getAttractions() = attractions.toList()

    fun requestNews() {
        RetrofitSingleton.eventService.getNews(language.toURL()).handleData(stateNews, news)
    }

    fun requestAttractions() {
        RetrofitSingleton.attractionService.getAttractions(language.toURL()).apply {
            handleData(stateAttraction, attractions)
        }
    }

    fun requestAllAPIs() {
        requestNews()
        requestAttractions()
    }

    private inline fun <reified T> Call<APIResponse<List<T>>>.handleData(
            state: MutableLiveData<RetrofitSingleton.State>,
            al: ArrayList<T>
    ) {
        printLog("request= ${request()}")

        enqueue(object : Callback<APIResponse<List<T>>> {
            init {
                printLog("Callback of ${T::class.java.simpleName} initialized")

                al.clear()

                state.value = RetrofitSingleton.State.STARTED
            }

            override fun onResponse(call: Call<APIResponse<List<T>>>, response: Response<APIResponse<List<T>>>) {
                printLog("onResponse, call= $call")
                printLog("onResponse, response= $response")

                if (response.isSuccessful) {
                    al.addAll(response.body()?.data ?: emptyList())

                    state.value = RetrofitSingleton.State.SUCCESSFUL
                } else {
                    state.value = RetrofitSingleton.State.UNSUCCESSFUL
                }
            }

            override fun onFailure(call: Call<APIResponse<List<T>>>, throwable: Throwable) {
                printLog("onFailure, call= $call")
                printLog("onFailure, throwable= $throwable")

                this@MainViewModel.msgThrowable = throwable.message ?: ""

                state.value = RetrofitSingleton.State.FAILED
            }
        })
    }
}
