package taipei.travel.taipeitour.util

import android.content.Context
import android.util.Log
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import taipei.travel.taipeitour.BaseFragment
import taipei.travel.taipeitour.FragTransType
import taipei.travel.taipeitour.fragment.MainFragment

class Utils {
    companion object {
        fun printBoshLog(msg: String) = Log.i("Bosh_Tag", msg)

        fun getColor(context: Context, @ColorRes colorRes: Int) = ContextCompat.getColor(context, colorRes)

        fun navigateFragment(
                fragmentManager: FragmentManager,
                fragment: BaseFragment,
                navigateWay: FragTransType,
                @IdRes containerId: Int = View.NO_ID
        ) {
            if (containerId == View.NO_ID) {
                if (navigateWay == FragTransType.ADD || navigateWay == FragTransType.REPLACE) {
                    throw IllegalArgumentException("Function navigateFragment is missing containerId!!")
                }
            }

            fragmentManager.beginTransaction().apply {
                if (fragment !is MainFragment) addToBackStack(null)

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
