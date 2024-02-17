package taipei.travel.taipeitour.base

import android.content.ClipData
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.annotation.ColorRes
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.Guideline
import androidx.core.os.BundleCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import taipei.travel.taipeitour.App
import taipei.travel.taipeitour.R
import taipei.travel.taipeitour.util.BoshLogs
import taipei.travel.taipeitour.util.Utils

abstract class BaseFragment<VB : ViewBinding>(
        private val inflateVB: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment(), BoshLogs {
    protected companion object {
        const val ARG_ATTRACTION = "key_attraction"
        const val ARG_WEB_TITLE = "key_web_title"
        const val ARG_URL = "key_str_url"
    }

    protected lateinit var vb: VB; private set

    override fun onAttach(context: Context) {
        super.onAttach(context)

        printLog("onAttach, context= $context")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        printLog("onCreate, savedInstanceState= $savedInstanceState")
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        printLog("onCreateView, inflate= $inflater, container= $container, savedInstanceState= $savedInstanceState")

        vb = inflateVB.invoke(inflater, container, false)

        initUI()

        return vb.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        printLog("onViewCreated, view= $view, savedInstanceState= $savedInstanceState")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        printLog("onViewStateRestored, savedInstanceState= $savedInstanceState")
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

    @CallSuper
    override fun onSaveInstanceState(outState: Bundle) {
        printLog("onSaveInstanceState, outState= $outState")
    }

    override fun onDestroyView() {
        printLog("onDestroyView")

        super.onDestroyView()
    }

    override fun onDestroy() {
        printLog("onDestroy")

        super.onDestroy()
    }

    override fun onDetach() {
        printLog("onDetach")

        super.onDetach()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        printLog("onConfigurationChanged, newConfig= $newConfig")
    }

    protected fun getColor(@ColorRes colorRes: Int) = Utils.getColor(requireActivity(), colorRes)

    protected fun switchFragment(newFragment: BaseFragment<out ViewBinding>) {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(
                    R.anim.slide_in_right,
                    0,
                    0,
                    R.anim.slide_out_right
            )

            add(vb.root.id, newFragment, newFragment.javaClass.simpleName)
            addToBackStack(null)
            hide(this@BaseFragment)
        }.commit()
    }

    protected fun popFragment() = requireActivity().supportFragmentManager.popBackStack()

    protected fun popFragmentWithAlert(msg: String) {
        popFragment()

        AlertDialog.Builder(requireActivity()).apply {
            setTitle(android.R.string.dialog_alert_title)
            setMessage(msg)
            setPositiveButton(android.R.string.ok, null)
        }.show()
    }

    protected fun showShortToast(cs: CharSequence) {
        Toast.makeText(requireActivity(), cs, Toast.LENGTH_SHORT).show()
    }

    protected fun copyToClipboard(csCopy: CharSequence, csToast: CharSequence) {
        App.instance.clipboardManager.setPrimaryClip(ClipData.newPlainText(null, csCopy))
        showShortToast(csToast)
    }

    protected fun Guideline.setPercentForOrientation() {
        val isLandscape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

        setGuidelinePercent(if (isLandscape) 0.2f else 0.1f)
    }

    protected fun View.setTooltipTextCompat(txt: CharSequence) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tooltipText = txt
        } else {
            setOnLongClickListener {
                showShortToast(txt)

                true
            }
        }
    }

    protected inline fun <reified T : Parcelable> Bundle.getParcelableCompat(key: String): T? {
        return BundleCompat.getParcelable(this, key, T::class.java)
    }

    protected inline fun <reified T : Parcelable> Bundle.getParcelableArrayListCompat(key: String): ArrayList<T>? {
        return BundleCompat.getParcelableArrayList(this, key, T::class.java)
    }

    /**
     * This function will execute in the [onCreateView] of [BaseFragment]
     */
    protected abstract fun initUI()
}
