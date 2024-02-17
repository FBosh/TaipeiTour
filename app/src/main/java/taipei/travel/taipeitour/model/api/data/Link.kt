package taipei.travel.taipeitour.model.api.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Link(
        val src: String,
        val subject: String
) : Parcelable
