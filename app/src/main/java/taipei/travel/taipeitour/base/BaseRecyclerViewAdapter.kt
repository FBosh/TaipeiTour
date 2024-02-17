package taipei.travel.taipeitour.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import taipei.travel.taipeitour.util.BoshLogs
import kotlin.math.max

abstract class BaseRecyclerViewAdapter<VB : ViewBinding, T>(
        private val inflateVB: (LayoutInflater, ViewGroup, Boolean) -> VB
) : RecyclerView.Adapter<BaseRecyclerViewAdapter<VB, T>.BaseViewHolder>(), BoshLogs {
    private val alData = arrayListOf<T>()

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(inflateVB.invoke(LayoutInflater.from(parent.context), parent, false))
    }

    final override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(alData[position])
    }

    override fun getItemCount() = alData.size

    fun add(data: T) {
        alData.add(data)
        notifyItemInserted(itemCount)
    }

    fun addAll(newData: List<T>) {
        alData.addAll(newData)
        notifyItemRangeInserted(0, newData.size)
    }

    fun removeAt(position: Int) {
        alData.removeAt(position)
        notifyItemRemoved(position)
    }

    fun replaceAll(newData: List<T>) {
        val originalSize = itemCount

        alData.apply {
            clear()
            addAll(newData)
        }

        notifyItemRangeChanged(0, max(originalSize, itemCount))
    }

    fun clear() {
        val originalSize = itemCount

        alData.clear()
        notifyItemRangeRemoved(0, originalSize)
    }

    fun getAllData() = alData.toList()

    protected abstract fun BaseViewHolder.onBind(data: T)

    /**
     * Generic [VB] type variable should be in the [BaseViewHolder] only,
     * otherwise it might be conflicts on views
     */
    inner class BaseViewHolder(val vb: VB) : RecyclerView.ViewHolder(vb.root)
}
