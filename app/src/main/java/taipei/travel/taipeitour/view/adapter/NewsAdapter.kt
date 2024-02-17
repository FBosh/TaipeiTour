package taipei.travel.taipeitour.view.adapter

import taipei.travel.taipeitour.base.BaseRecyclerViewAdapter
import taipei.travel.taipeitour.databinding.RecyclableNewsBinding
import taipei.travel.taipeitour.model.api.data.News
import kotlin.math.min

class NewsAdapter(
        private val clickItem: (News) -> Unit = {}
) : BaseRecyclerViewAdapter<RecyclableNewsBinding, News>(
        RecyclableNewsBinding::inflate
) {
    override fun getItemCount() = min(getAllData().size, 3)

    override fun BaseViewHolder.onBind(data: News) {
        vb.tvTitle.text = data.title

        vb.tvInfo.text = data.description

        itemView.setOnClickListener {
            clickItem.invoke(data)
        }
    }
}
