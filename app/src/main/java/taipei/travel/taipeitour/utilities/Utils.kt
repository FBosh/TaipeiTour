package taipei.travel.taipeitour.utilities

import android.content.Context
import android.util.Log
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

class Utils {
    companion object {
        fun printBoshLog(msg: String) = Log.i("Bosh_Tag", msg)

        fun getColor(context: Context, @ColorRes colorRes: Int) = ContextCompat.getColor(context, colorRes)
    }
}
