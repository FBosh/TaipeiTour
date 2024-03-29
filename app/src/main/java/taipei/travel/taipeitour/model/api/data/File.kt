package taipei.travel.taipeitour.model.api.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class File(
        val src: String,
        val subject: String,
        val ext: String
) : Parcelable
