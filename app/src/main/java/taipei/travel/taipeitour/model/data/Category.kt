package taipei.travel.taipeitour.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
        val id: Long,
        val name: String
) : Parcelable
