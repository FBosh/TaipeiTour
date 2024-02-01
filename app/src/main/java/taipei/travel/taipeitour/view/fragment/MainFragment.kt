package taipei.travel.taipeitour.view.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import taipei.travel.taipeitour.Language
import taipei.travel.taipeitour.R
import taipei.travel.taipeitour.base.BaseFragment
import taipei.travel.taipeitour.databinding.FragMainBinding
import taipei.travel.taipeitour.model.api.RetrofitSingleton
import taipei.travel.taipeitour.model.data.APIResponse
import taipei.travel.taipeitour.model.data.Attraction
import taipei.travel.taipeitour.view.adapter.AttractionsAdapter

class MainFragment : BaseFragment<FragMainBinding>(FragMainBinding::inflate) {
    private companion object {
        const val KEY_ATTRACTIONS = "key_attractions"
        const val KEY_LANGUAGE = "key_language"
    }

    private var language = Language.ZH_TW

    private val adapterAttractions = AttractionsAdapter {
        switchFragment(AttractionFragment.newInstance(it))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        language = Language.valueOf(savedInstanceState?.getString(KEY_LANGUAGE)
                ?: Language.ZH_TW.name)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            requestAttractions()
        } else {
            savedInstanceState.getParcelableArrayListCompat<Attraction>(KEY_ATTRACTIONS).also { savedAttractions ->
                if (!savedAttractions.isNullOrEmpty()) {
                    adapterAttractions.addAll(savedAttractions.toList())
                } else {
                    requestAttractions()
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.apply {
            putParcelableArrayList(KEY_ATTRACTIONS, adapterAttractions.getAllData())
            putString(KEY_LANGUAGE, language.name)
        }

        super.onSaveInstanceState(outState)
    }

    override fun initUI() {
        vb.tvTitle.text = language.title

        vb.ibRefresh.apply {
            setTooltipTextCompat(getString(R.string.text_refresh))

            visibility = View.GONE

            setOnClickListener {
                requestAttractions()
            }
        }

        vb.ibLanguage.apply {
            setTooltipTextCompat(getString(R.string.text_language))

            setOnClickListener {
                PopupMenu(requireActivity(), this).apply {
                    Language.entries.forEach {
                        menu.add(it.localeName)
                    }

                    setOnMenuItemClickListener { item ->
                        language = Language.getOrDefault(item.title.toString())

                        requestAttractions()
                        vb.tvTitle.text = language.title

                        true
                    }

                    show()
                }
            }
        }

        vb.guidelineToolbar.setPercentForOrientation()

        vb.rvContainer.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = adapterAttractions
        }

        vb.tvError.visibility = View.GONE

        vb.progressbar.visibility = View.GONE
    }

    private fun requestAttractions() {
        RetrofitSingleton.attractionService.getAttractions(language.toURL()).apply {
            printLog("request= ${request()}")

            enqueue(object : Callback<APIResponse<List<Attraction>>> {
                init {
                    vb.progressbar.visibility = View.VISIBLE
                    vb.tvError.visibility = View.GONE
                    vb.ibRefresh.visibility = View.GONE
                    adapterAttractions.clear()
                    vb.ibLanguage.isClickable = false
                }

                override fun onResponse(call: Call<APIResponse<List<Attraction>>>, response: Response<APIResponse<List<Attraction>>>) {
                    printLog("onResponse, call= $call}")
                    printLog("onResponse, response= $response}")

                    vb.progressbar.visibility = View.GONE

                    if (response.isSuccessful) {
                        adapterAttractions.addAll(response.body()?.data ?: listOf())

                        vb.tvError.visibility = View.GONE
                        vb.ibRefresh.visibility = View.GONE
                    } else {
                        vb.tvError.visibility = View.VISIBLE
                        vb.ibRefresh.visibility = View.VISIBLE

                        vb.tvError.text = getString(R.string.text_fetching_error)
                    }

                    vb.ibLanguage.isClickable = true
                }

                override fun onFailure(call: Call<APIResponse<List<Attraction>>>, throwable: Throwable) {
                    printLog("onFailure(.....), call= $call")
                    printLog("onFailure(.....), throwable= $throwable")

                    vb.progressbar.visibility = View.GONE

                    vb.tvError.visibility = View.VISIBLE
                    vb.ibRefresh.visibility = View.VISIBLE

                    vb.ibLanguage.isClickable = true

                    vb.tvError.text = getString(
                            if (throwable.message?.contains(getString(R.string.text_timeout)) == true) {
                                R.string.text_timeout_error
                            } else {
                                R.string.text_unknown_error
                            }
                    )
                }
            })
        }
    }
}
