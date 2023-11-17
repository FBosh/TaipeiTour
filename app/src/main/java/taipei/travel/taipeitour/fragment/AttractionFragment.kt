package taipei.travel.taipeitour.fragment

import taipei.travel.taipeitour.BaseFragment
import taipei.travel.taipeitour.R
import taipei.travel.taipeitour.databinding.FragAttractionBinding

class AttractionFragment : BaseFragment() {
    override val layoutRes = R.layout.frag_attraction
    override val vb by lazy { FragAttractionBinding.bind(requireView()) }

    override fun init() {
        //
    }

    override fun initUI() {
        vb.ibBackward.setOnClickListener {
            performBackPressed()
        }
    }
}
