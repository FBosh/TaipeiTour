package taipei.travel.taipeitour.activity

import android.os.Bundle
import taipei.travel.taipeitour.BaseActivity
import taipei.travel.taipeitour.databinding.ActivityMainBinding
import taipei.travel.taipeitour.fragment.MainFragment

class MainActivity : BaseActivity() {
    private val vb by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(vb.root)

        if (savedInstanceState == null) startFirstFragment(vb.fragContainer.id, MainFragment())
    }
}
