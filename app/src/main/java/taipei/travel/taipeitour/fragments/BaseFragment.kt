package taipei.travel.taipeitour.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import taipei.travel.taipeitour.utilities.Utils

abstract class BaseFragment : Fragment() {
    protected abstract val layoutRes: Int
    protected abstract val vb: ViewBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(layoutRes, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    protected fun printBoshLog(msg: String) = Utils.printBoshLog(msg)

    protected fun getColor(@ColorRes colorRes: Int) = Utils.getColor(requireActivity(), colorRes)

    protected abstract fun initUI()
}
