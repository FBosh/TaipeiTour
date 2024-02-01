package taipei.travel.taipeitour.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Pano(
        val id: Long,
        val title: String,
        val url: String,
        val modified: Date
) : Parcelable
