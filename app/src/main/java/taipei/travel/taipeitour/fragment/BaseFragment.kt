package taipei.travel.taipeitour.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import taipei.travel.taipeitour.util.Utils

abstract class BaseFragment : Fragment() {
    abstract val layoutRes: Int
    protected abstract val vb: ViewBinding

    protected val fm by lazy { requireActivity().supportFragmentManager }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        init()

        return inflater.inflate(layoutRes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUI()
    }

    protected fun printBoshLog(msg: String) = Utils.printBoshLog(msg)

    protected fun getColor(@ColorRes colorRes: Int) = Utils.getColor(requireActivity(), colorRes)

    protected abstract fun init()

    protected abstract fun initUI()
}
