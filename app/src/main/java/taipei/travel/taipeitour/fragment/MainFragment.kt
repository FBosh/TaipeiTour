package taipei.travel.taipeitour.fragment

import androidx.appcompat.widget.PopupMenu
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import taipei.travel.taipeitour.FragTransType
import taipei.travel.taipeitour.Language
import taipei.travel.taipeitour.R
import taipei.travel.taipeitour.api.RetrofitSingleton
import taipei.travel.taipeitour.databinding.FragMainBinding
import taipei.travel.taipeitour.model.APIResponse
import taipei.travel.taipeitour.model.Attraction
import taipei.travel.taipeitour.util.Utils

class MainFragment(
        private val language: Language = Language.ZH_TW
) : BaseFragment() {
    override val layoutRes = R.layout.frag_main
    override val vb by lazy { FragMainBinding.bind(requireView()) }

    init {
        printBoshLog("MainFragment, $this")
    }

    override fun init() {
        getAttractions()
    }

    override fun initUI() {
        vb.toolbar.apply {
            //Test
            setOnClickListener {
                Utils.navigateFragment(fm, vb.root.id, TourFragment(), FragTransType.ADD)
            }
        }

        vb.ibTranslate.setOnClickListener {
            PopupMenu(requireActivity(), vb.ibTranslate).apply {
                Language.entries.forEach {
                    menu.add(it.toString())
                }

                setOnMenuItemClickListener { item ->
                    val languageSelected = Language.getSafety(item.title.toString()).also {
                        if (it == language) return@setOnMenuItemClickListener false
                    }

                    Utils.navigateFragment(fm, vb.root.id, MainFragment(languageSelected), FragTransType.REPLACE)

                    true
                }

                show()
            }
        }
    }

    private fun getAttractions() {
        RetrofitSingleton.attractionService.getAttractions(language.toString()).apply {
            printBoshLog("request= ${request()}")

            enqueue(object : Callback<APIResponse<List<Attraction>>> {
                override fun onResponse(call: Call<APIResponse<List<Attraction>>>, response: Response<APIResponse<List<Attraction>>>) {
                    printBoshLog("onResponse, call= $call}")
                    printBoshLog("onResponse, response= $response}")
                    printBoshLog("onResponse, response.body= ${response.body()}")
                    printBoshLog("onResponse, response.isSuccessful= ${response.isSuccessful}")
                    printBoshLog("onResponse, response.raw= ${response.raw()}")
                    printBoshLog("onResponse, response.errorBody= ${response.errorBody()}")
                    printBoshLog("onResponse, response.msg= ${response.message()}")
                }

                override fun onFailure(call: Call<APIResponse<List<Attraction>>>, throwable: Throwable) {
                    printBoshLog("onFailure(.....), call= $call")
                    printBoshLog("onFailure(.....), throwable= $throwable")
                }
            })
        }
    }
}
