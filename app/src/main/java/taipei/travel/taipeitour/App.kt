package taipei.travel.taipeitour

import android.app.Application
import android.content.ClipboardManager
import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatDelegate
import taipei.travel.taipeitour.util.BoshLogs

class App : Application(), BoshLogs {
    companion object {
        const val TAG = "App"

        lateinit var instance: App; private set
    }

    val appHandler = Handler(Looper.getMainLooper())

    val clipboardManager by lazy { getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager }

    override fun onCreate() {
        super.onCreate()

        printLog("$TAG onCreate()")

        instance = this

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}
