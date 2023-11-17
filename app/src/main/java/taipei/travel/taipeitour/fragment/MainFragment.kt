package taipei.travel.taipeitour.fragment

import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import taipei.travel.taipeitour.BaseFragment
import taipei.travel.taipeitour.FragTransType
import taipei.travel.taipeitour.Language
import taipei.travel.taipeitour.R
import taipei.travel.taipeitour.adapter.AttractionsAdapter
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
        printBoshLog("MainFragment initialized= $this")
    }

    override fun init() {
        //
    }

    override fun initUI() {
        vb.toolbar.apply {
            //Test
            setOnClickListener {
                Utils.navigateFragment(fm, AttractionFragment(), FragTransType.ADD, vb.root.id)
            }
        }

        vb.ibTranslate.setOnClickListener {
            PopupMenu(requireActivity(), vb.ibTranslate).apply {
                Language.entries.forEach {
                    menu.add(it.description)
                }

                setOnMenuItemClickListener { item ->
                    val languageSelected = Language.getByDescriptionSafely(item.title.toString()).also {
                        if (it == language) return@setOnMenuItemClickListener false
                    }

                    Utils.navigateFragment(fm, this@MainFragment, FragTransType.REMOVE)
                    Utils.navigateFragment(fm, MainFragment(languageSelected), FragTransType.REPLACE, vb.root.id)

                    true
                }

                show()
            }
        }

        vb.rvContainer.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = AttractionsAdapter()
            visibility = View.GONE
        }

        vb.tvTitle.text = language.title

        vb.tvError.visibility = View.GONE

        vb.ibRefresh.apply {
            visibility = View.GONE
            setOnClickListener {
                requestAttractions()
            }
        }

        requestAttractions()
    }

    private fun requestAttractions() {
        RetrofitSingleton.attractionService.getAttractions(language.toString()).apply {
            printBoshLog("request= ${request()}")

            enqueue(object : Callback<APIResponse<List<Attraction>>> {
                init {
                    vb.progressbar.visibility = View.VISIBLE
                    vb.tvError.visibility = View.GONE
                    vb.ibRefresh.visibility = View.GONE
                }

                override fun onResponse(call: Call<APIResponse<List<Attraction>>>, response: Response<APIResponse<List<Attraction>>>) {
                    printBoshLog("onResponse, call= $call}")
                    printBoshLog("onResponse, response= $response}")
//                    printBoshLog("onResponse, response.body= ${response.body()}")
//                    printBoshLog("onResponse, response.isSuccessful= ${response.isSuccessful}")
//                    printBoshLog("onResponse, response.raw= ${response.raw()}")
//                    printBoshLog("onResponse, response.errorBody= ${response.errorBody()}")
//                    printBoshLog("onResponse, response.msg= ${response.message()}")

                    vb.progressbar.visibility = View.GONE

                    if (response.isSuccessful) {
                        vb.rvContainer.apply {
                            val result = response.body()?.data ?: listOf()

                            (adapter as AttractionsAdapter).updateAttractions(result)
                            visibility = View.VISIBLE
                        }

                        vb.tvError.visibility = View.GONE
                        vb.ibRefresh.visibility = View.GONE
                    } else {
                        vb.rvContainer.visibility = View.GONE
                        vb.tvError.visibility = View.VISIBLE
                        vb.ibRefresh.visibility = View.VISIBLE
                    }
                }

                override fun onFailure(call: Call<APIResponse<List<Attraction>>>, throwable: Throwable) {
                    printBoshLog("onFailure(.....), call= $call")
                    printBoshLog("onFailure(.....), throwable= $throwable")

                    vb.progressbar.visibility = View.GONE
                    vb.rvContainer.visibility = View.GONE

                    vb.tvError.visibility = View.VISIBLE
                    vb.ibRefresh.visibility = View.VISIBLE
                }
            })
        }
    }
}
