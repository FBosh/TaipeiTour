package taipei.travel.taipeitour.view.adapter

import android.view.View
import taipei.travel.taipeitour.R
import taipei.travel.taipeitour.base.BaseRecyclerViewAdapter
import taipei.travel.taipeitour.databinding.RecyclableAttractionBinding
import taipei.travel.taipeitour.model.data.Attraction
import taipei.travel.taipeitour.util.Utils

class AttractionsAdapter(
        private val clickItem: (Attraction) -> Unit = {}
) : BaseRecyclerViewAdapter<RecyclableAttractionBinding, Attraction>(
        RecyclableAttractionBinding::inflate
) {
    override fun BaseViewHolder.onBind(data: Attraction) {
        val strImageURL = data.images.firstOrNull()?.src ?: ""

        vb.tvTitle.text = data.name
        vb.tvInfo.text = data.introduction
        Utils.setImageViewByGlide(vb.root.context, strImageURL, R.mipmap.placeholder_pic, vb.ivAttraction)

        itemView.setOnClickListener {
            clickItem.invoke(data)
        }

        vb.vDivider.visibility = if (layoutPosition != itemCount - 1) View.VISIBLE else View.GONE
    }
}
