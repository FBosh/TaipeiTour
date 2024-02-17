package taipei.travel.taipeitour.view.fragment

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.TextView
import com.google.android.material.tabs.TabLayoutMediator
import taipei.travel.taipeitour.R
import taipei.travel.taipeitour.base.BaseFragment
import taipei.travel.taipeitour.databinding.FragAttractionBinding
import taipei.travel.taipeitour.model.api.data.Attraction
import taipei.travel.taipeitour.view.adapter.ImagesAdapter

class AttractionFragment : BaseFragment<FragAttractionBinding>(FragAttractionBinding::inflate) {
    companion object {
        fun newInstance(attraction: Attraction) = AttractionFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_ATTRACTION, attraction)
            }
        }
    }

    private val attraction by lazy {
        arguments?.getParcelableCompat(ARG_ATTRACTION) ?: Attraction.UNDEFINED
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!attraction.isValid()) popFragmentWithAlert(getString(R.string.text_invalid_attraction))
    }

    override fun initUI() {
        fun TextView.setTextWithCheck(cs: CharSequence, prefix: CharSequence = "") {
            if (cs.isEmpty()) visibility = View.GONE

            text = StringBuilder().apply {
                if (prefix.isNotEmpty()) append(prefix)
                append(cs)
            }
        }

        fun View.setListenersForURL() {
            setOnClickListener {
                switchFragment(WebFragment.newInstance(attraction.name, attraction.officialSite))
            }

            setOnLongClickListener {
                copyToClipboard(attraction.officialSite, getString(R.string.text_link_copied))

                true
            }
        }

        vb.ibBackward.apply {
            setTooltipTextCompat(getString(R.string.text_back))

            setOnClickListener {
                popFragment()
            }
        }

        vb.tvTitle.setTextWithCheck(attraction.name)

        vb.guidelineToolbar.setPercentForOrientation()

        if (attraction.images.isNotEmpty()) {
            vb.vpAttraction.adapter = ImagesAdapter().apply { addAll(attraction.images.map { it.src }) }
            TabLayoutMediator(vb.layoutTab, vb.vpAttraction) { _, _ -> }.attach()
        } else {
            vb.vpAttraction.visibility = View.GONE
            vb.layoutTab.visibility = View.GONE
        }

        vb.tvIntro.setTextWithCheck(attraction.introduction)

        vb.tvAddress.setTextWithCheck(attraction.address, "${getString(R.string.text_address)}\n")

        vb.layoutUrl.apply {
            if (attraction.officialSite.isEmpty()) visibility = View.GONE
        }

        vb.tvUrl.apply {
            if (attraction.officialSite.isEmpty()) return@apply

            text = SpannableString(getString(R.string.text_official_site)).apply {
                setSpan(UnderlineSpan(), 0, length, 0)
            }

            movementMethod = null

            setListenersForURL()
        }

        vb.ivUrl.apply {
            if (attraction.officialSite.isEmpty()) return@apply

            setListenersForURL()
        }

        vb.tvLastUpdatedTime.setTextWithCheck(
                attraction.modified.toString(),
                "${getString(R.string.text_last_updated_time)}\n"
        )
    }
}
