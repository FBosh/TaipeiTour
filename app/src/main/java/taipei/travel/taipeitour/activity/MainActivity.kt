package taipei.travel.taipeitour.activity

import taipei.travel.taipeitour.BaseActivity
import taipei.travel.taipeitour.FragTransType
import taipei.travel.taipeitour.databinding.ActivityMainBinding
import taipei.travel.taipeitour.fragment.MainFragment
import taipei.travel.taipeitour.util.Utils

class MainActivity : BaseActivity() {
    override val vb by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun initUI() {
        Utils.navigateFragment(fm, MainFragment(), FragTransType.REPLACE, vb.layoutFragContainer.id)
    }
}
