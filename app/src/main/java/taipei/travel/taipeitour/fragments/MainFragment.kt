package taipei.travel.taipeitour.fragments

import taipei.travel.taipeitour.R
import taipei.travel.taipeitour.databinding.FragMainBinding

class MainFragment : BaseFragment() {
    override val layoutRes = R.layout.frag_main
    override val vb by lazy { FragMainBinding.bind(requireView()) }

    override fun initUI() {
        //
    }
}
