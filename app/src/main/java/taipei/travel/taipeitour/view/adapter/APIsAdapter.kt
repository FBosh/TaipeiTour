package taipei.travel.taipeitour.view.adapter

import android.content.Context
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import taipei.travel.taipeitour.base.BaseRecyclerViewAdapter
import taipei.travel.taipeitour.databinding.RecyclableApiBinding
import taipei.travel.taipeitour.model.data.OuterRV

class APIsAdapter(private val ctx: Context) : BaseRecyclerViewAdapter<RecyclableApiBinding, OuterRV<out Parcelable>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BaseViewHolder(
            RecyclableApiBinding.inflate(LayoutInflater.from(parent.context),
                    parent,
                    false
            )
    ).apply {
        vb.rvContainer.layoutManager = LinearLayoutManager(ctx)
    }

    override fun BaseViewHolder.onBind(data: OuterRV<out Parcelable>) {
        vb.tvTitle.apply {
            text = data.title
            visibility = if (data.adapter.itemCount > 0) View.VISIBLE else View.GONE
        }

        vb.rvContainer.adapter = data.adapter
    }
}
