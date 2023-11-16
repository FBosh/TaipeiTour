package taipei.travel.taipeitour.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import taipei.travel.taipeitour.databinding.RecyclableTourBinding
import taipei.travel.taipeitour.model.Attraction

class AttractionsAdapter : BaseRecyclerViewAdapter<AttractionsAdapter.ViewHolder>() {
    private val attractions = arrayListOf<Attraction>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecyclableTourBinding.inflate(LayoutInflater.from(parent.context), parent, false))
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

    inner class ViewHolder(private val vb: RecyclableTourBinding) : RecyclerView.ViewHolder(vb.root) {
        fun bind(attraction: Attraction) {
            val strImageURL = attraction.images.firstOrNull()?.src ?: ""

            vb.tvTitle.text = attraction.name
            vb.tvInfo.text = attraction.introduction
            setImageViewByGlide(vb.root.context, strImageURL, vb.ivTour)
        }
    }
}
