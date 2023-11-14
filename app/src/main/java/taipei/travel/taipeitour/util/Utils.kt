package taipei.travel.taipeitour.util

import android.content.Context
import android.util.Log
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import taipei.travel.taipeitour.FragTransType
import taipei.travel.taipeitour.fragment.BaseFragment

class Utils {
    companion object {
        fun printBoshLog(msg: String) = Log.i("Bosh_Tag", msg)

        fun getColor(context: Context, @ColorRes colorRes: Int) = ContextCompat.getColor(context, colorRes)

        fun navigateFragment(
                fragmentManager: FragmentManager,
                @IdRes containerId: Int,
                fragment: BaseFragment,
                navigateWay: FragTransType
        ) {
            fragmentManager.beginTransaction().apply {
                when (navigateWay) {
                    FragTransType.ADD -> add(containerId, fragment, fragment.layoutRes.toString())
                    FragTransType.REPLACE -> replace(containerId, fragment, fragment.layoutRes.toString())
                    FragTransType.REMOVE -> remove(fragment)
                    FragTransType.ATTACH -> attach(fragment)
                    FragTransType.DETACH -> detach(fragment)
                    FragTransType.HIDE -> hide(fragment)
                    FragTransType.SHOW -> show(fragment)
                }
            }.commit()
        }
    }
}
