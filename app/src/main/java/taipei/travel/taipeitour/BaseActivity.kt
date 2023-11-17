package taipei.travel.taipeitour

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import taipei.travel.taipeitour.util.Utils

abstract class BaseActivity : AppCompatActivity() {
    protected abstract val vb: ViewBinding

    protected val fm = supportFragmentManager

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
