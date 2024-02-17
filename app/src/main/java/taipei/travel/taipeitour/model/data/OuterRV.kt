package taipei.travel.taipeitour.model.data

import android.os.Parcelable
import androidx.viewbinding.ViewBinding
import taipei.travel.taipeitour.base.BaseRecyclerViewAdapter

data class OuterRV<T : Parcelable>(
        val position: Int,
        val title: String,
        val adapter: BaseRecyclerViewAdapter<out ViewBinding, T>
)
