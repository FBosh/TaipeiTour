package taipei.travel.taipeitour.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
        val src: String,
        val sub: String,
        val ext: String
) : Parcelable
