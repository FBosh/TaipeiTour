package taipei.travel.taipeitour.fragment

import taipei.travel.taipeitour.BaseFragment
import taipei.travel.taipeitour.R
import taipei.travel.taipeitour.databinding.FragWebViewBinding

class WebViewFragment : BaseFragment() {
    override val layoutRes = R.layout.frag_web_view
    override val vb by lazy { FragWebViewBinding.bind(requireView()) }

    override fun init() {
        //
    }

    override fun initUI() {
        //
    }
}
