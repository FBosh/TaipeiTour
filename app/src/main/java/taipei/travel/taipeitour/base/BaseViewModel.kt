package taipei.travel.taipeitour.base

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import taipei.travel.taipeitour.util.BoshLogs

abstract class BaseViewModel : ViewModel(), BoshLogs {
    init {
        initLog()
    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()

        printLog("onCleared")
    }

    private fun initLog() {
        printLog("${javaClass.simpleName} initialized")
    }
}
