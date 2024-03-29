package taipei.travel.taipeitour.view.adapter

import taipei.travel.taipeitour.R
import taipei.travel.taipeitour.base.BaseRecyclerViewAdapter
import taipei.travel.taipeitour.databinding.RecyclableImageBinding
import taipei.travel.taipeitour.util.Utils

class ImagesAdapter : BaseRecyclerViewAdapter<RecyclableImageBinding, String>(
        RecyclableImageBinding::inflate
) {
    override fun BaseViewHolder.onBind(data: String) {
        Utils.setImageViewByGlide(itemView.context, data, R.mipmap.placeholder_pic_big, vb.ivImage)
    }
}
