package taipei.travel.taipeitour.fragment

import taipei.travel.taipeitour.R
import taipei.travel.taipeitour.databinding.FragTourBinding

class TourFragment : BaseFragment() {
    override val layoutRes = R.layout.frag_tour
    override val vb by lazy { FragTourBinding.bind(requireView()) }

    override fun init() {
        //
    }

    override fun initUI() {
        vb.ibBackward.setOnClickListener {
            performBackPressed()
        }
    }
}
