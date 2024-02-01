package taipei.travel.taipeitour.view.activity

import android.os.Bundle
import taipei.travel.taipeitour.base.BaseActivity
import taipei.travel.taipeitour.databinding.ActivityMainBinding
import taipei.travel.taipeitour.view.fragment.MainFragment

class MainActivity : BaseActivity() {
    private val vb by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(vb.root)

        if (savedInstanceState == null) startFirstFragment(vb.fragContainer.id, MainFragment())
    }
}
