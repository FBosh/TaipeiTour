package taipei.travel.taipeitour

import android.app.Application
import android.os.Handler
import android.os.Looper

class App : Application() {
    companion object {
        const val TAG = "App"

        lateinit var instance: App; private set
    }

    val appHandler = Handler(Looper.getMainLooper())

    override fun onCreate() {
        super.onCreate()

        instance = this
    }
}
