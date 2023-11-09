package taipei.travel.taipeitour.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import taipei.travel.taipeitour.utilities.Utils

abstract class BaseActivity : AppCompatActivity() {
    protected abstract val vb: ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() {
        setContentView(vb.root)

        initUI()
    }

    protected fun printBoshLog(msg: String) = Utils.printBoshLog(msg)

    protected abstract fun initUI()
}
