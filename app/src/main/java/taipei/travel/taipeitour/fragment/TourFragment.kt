package taipei.travel.taipeitour.fragment

import androidx.activity.OnBackPressedCallback
import taipei.travel.taipeitour.FragTransType
import taipei.travel.taipeitour.R
import taipei.travel.taipeitour.databinding.FragTourBinding
import taipei.travel.taipeitour.util.Utils

class TourFragment : BaseFragment() {
    override val layoutRes = R.layout.frag_tour
    override val vb by lazy { FragTourBinding.bind(requireView()) }

    override fun init() {
        addOnBackPressedListener()
    }

    override fun initUI() {
        vb.ibBackward.setOnClickListener {
            removeThisFragment()
        }
    }

    private fun addOnBackPressedListener() {
        requireActivity().onBackPressedDispatcher.addCallback(
                this,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        removeThisFragment()
                    }
                }
        )
    }

    private fun removeThisFragment() {
        fm.findFragmentByTag(layoutRes.toString()).also {
            if (it == null) return

            Utils.navigateFragment(fm, vb.root.id, it as BaseFragment, FragTransType.REMOVE)
        }
    }
}
