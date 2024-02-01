package taipei.travel.taipeitour.base

import android.content.res.Configuration
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import taipei.travel.taipeitour.util.BoshLogs

abstract class BaseActivity : AppCompatActivity(), BoshLogs {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        printLog("onCreate, savedInstanceState= $savedInstanceState")
    }

    override fun onRestart() {
        super.onRestart()

        printLog("onRestart")
    }

    override fun onStart() {
        super.onStart()

        printLog("onStart")
    }

    override fun onResume() {
        super.onResume()

        printLog("onResume")
    }

    override fun onPause() {
        printLog("onPause")

        super.onPause()
    }

    override fun onStop() {
        printLog("onStop")

        super.onStop()
    }

    override fun onDestroy() {
        printLog("onDestroy")

        super.onDestroy()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        printLog("onConfigurationChanged, newConfig= $newConfig")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        printLog("onSaveInstanceState, outState= $outState")
    }

    protected fun startFirstFragment(@IdRes containerId: Int, firstFragment: BaseFragment<*>) {
        supportFragmentManager.beginTransaction().apply {
            replace(containerId, firstFragment, firstFragment.javaClass.simpleName)
        }.commit()
    }
}
