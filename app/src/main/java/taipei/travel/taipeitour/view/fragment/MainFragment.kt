package taipei.travel.taipeitour.view.fragment

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import taipei.travel.taipeitour.R
import taipei.travel.taipeitour.base.BaseFragment
import taipei.travel.taipeitour.base.BaseRecyclerViewAdapter
import taipei.travel.taipeitour.databinding.FragMainBinding
import taipei.travel.taipeitour.model.api.RetrofitSingleton
import taipei.travel.taipeitour.model.data.OuterRV
import taipei.travel.taipeitour.util.APIType
import taipei.travel.taipeitour.util.SelectedLanguage
import taipei.travel.taipeitour.view.adapter.APIsAdapter
import taipei.travel.taipeitour.view.adapter.AttractionsAdapter
import taipei.travel.taipeitour.view.adapter.NewsAdapter
import taipei.travel.taipeitour.view.custom.CustomizedLinearLayoutManager
import taipei.travel.taipeitour.viewmodel.MainViewModel

class MainFragment : BaseFragment<FragMainBinding>(FragMainBinding::inflate) {
    private val vm by viewModels<MainViewModel>()

    private val adapterNews = NewsAdapter {
        switchFragment(WebFragment.newInstance(it.title, it.url))
    }

    private val adapterAttractions = AttractionsAdapter {
        switchFragment(AttractionFragment.newInstance(it))
    }

    private val adapterOuter by lazy { APIsAdapter(requireActivity()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAPIStatesObservers()
    }

    override fun initUI() {
        vb.tvTitle.text = vm.language.title

        vb.ibRefresh.apply {
            setTooltipTextCompat(getString(R.string.text_refresh))

            visibility = View.GONE

            setOnClickListener {
                requestAllAPIs()
            }
        }

        vb.ibLanguage.apply {
            setTooltipTextCompat(getString(R.string.text_language))

            setOnClickListener {
                PopupMenu(requireActivity(), this).apply {
                    SelectedLanguage.entries.forEach {
                        menu.add(it.localeName)
                    }

                    setOnMenuItemClickListener { item ->
                        vm.language = SelectedLanguage.getOrDefault(item.title.toString())

                        requestAllAPIs()
                        vb.tvTitle.text = vm.language.title

                        true
                    }

                    show()
                }
            }
        }

        vb.guidelineToolbar.setPercentForOrientation()

        vb.rvContainer.apply {
            adapter = adapterOuter
            layoutManager = CustomizedLinearLayoutManager(requireActivity())
            itemAnimator = null
        }

        vb.tvError.visibility = View.GONE

        vb.progressbar.visibility = View.GONE
    }

    private fun initAPIStatesObservers() {
        observeAPIState(
                0,
                vm.stateNews,
                APIType.NEWS,
                adapterNews,
                vm::requestNews,
                vm::getNews
        )

        observeAPIState(
                1,
                vm.stateAttraction,
                APIType.ATTRACTION,
                adapterAttractions,
                vm::requestAttractions,
                vm::getAttractions
        )
    }

    private fun requestAllAPIs() {
        adapterOuter.clear()

        vm.requestAllAPIs()
    }

    private inline fun <reified T : Parcelable> observeAPIState(
            position: Int,
            liveState: LiveData<RetrofitSingleton.State>,
            typeAPI: APIType,
            adapter: BaseRecyclerViewAdapter<*, T>,
            crossinline request: () -> Unit,
            crossinline getList: () -> List<T>
    ) {
        liveState.observe(viewLifecycleOwner) { state ->
            val title = when (typeAPI) {
                APIType.NEWS -> vm.language.titleNews
                APIType.ATTRACTION -> vm.language.titleAttractions
            }

            when (state) {
                RetrofitSingleton.State.NONE -> {
                    request.invoke()
                }

                RetrofitSingleton.State.STARTED -> {
                    vb.progressbar.visibility = View.VISIBLE
                    vb.tvError.visibility = View.GONE
                    vb.ibRefresh.visibility = View.GONE
                    vb.ibLanguage.isClickable = false
                    vb.rvContainer.visibility = View.GONE

                    adapter.clear()

                    adapterOuter.apply {
                        if (itemCount > position) {
                            removeAt(position)
                        }
                    }
                }

                RetrofitSingleton.State.SUCCESSFUL -> {
                    vb.progressbar.visibility = View.GONE
                    vb.tvError.visibility = View.GONE
                    vb.ibRefresh.visibility = View.GONE
                    vb.ibLanguage.isClickable = true
                    vb.rvContainer.visibility = View.VISIBLE

                    adapter.addAll(getList.invoke())

                    adapterOuter.add(OuterRV(position, title, adapter))
                }

                else -> {
                    vb.progressbar.visibility = View.GONE

                    vb.tvError.visibility = View.VISIBLE
                    vb.ibRefresh.visibility = View.VISIBLE

                    vb.ibLanguage.isClickable = true

                    vb.rvContainer.visibility = View.GONE

                    vb.tvError.text = getString(
                            if (state == RetrofitSingleton.State.UNSUCCESSFUL) {
                                R.string.text_fetching_error
                            } else if (vm.msgThrowable.contains(getString(R.string.text_timeout))) {
                                R.string.text_timeout_error
                            } else {
                                R.string.text_unknown_error
                            }
                    )

                    adapter.clear()

                    adapterOuter.apply {
                        if (itemCount > position) {
                            removeAt(position)
                        }
                    }
                }
            }
        }
    }
}
