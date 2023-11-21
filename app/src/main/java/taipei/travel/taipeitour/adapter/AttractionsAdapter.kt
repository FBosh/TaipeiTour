package taipei.travel.taipeitour.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import taipei.travel.taipeitour.R
import taipei.travel.taipeitour.databinding.RecyclableAttractionBinding
import taipei.travel.taipeitour.model.Attraction
import taipei.travel.taipeitour.util.Utils

class AttractionsAdapter : BaseRecyclerViewAdapter<AttractionsAdapter.ViewHolder>() {
    private val attractions = arrayListOf<Attraction>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return ViewHolder(RecyclableAttractionBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(attractions[position])
    }

    override fun getItemCount() = attractions.size

    fun updateAttractions(new: List<Attraction>) {
        clear()
        attractions.addAll(new)
        notifyItemRangeChanged(0, itemCount)
    }

    fun clear() {
        notifyItemRangeRemoved(0, itemCount)
        attractions.clear()
    }

    inner class ViewHolder(private val vb: RecyclableAttractionBinding) : RecyclerView.ViewHolder(vb.root) {
        fun bind(attraction: Attraction) {
            val strImageURL = attraction.images.firstOrNull()?.src ?: ""

            vb.tvTitle.text = attraction.name
            vb.tvInfo.text = attraction.introduction
            Utils.setImageViewByGlide(vb.root.context, strImageURL, R.mipmap.placeholder_pic, vb.ivAttraction)
        }
    }
}
