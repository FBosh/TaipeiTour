package taipei.travel.taipeitour.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class News(
        val id: Int,
        val title: String,
        val description: String,
        val begin: Date?,
        val end: Date?,
        val posted: Date,
        val modified: Date,
        val url: String,
        val files: List<File>,
        val links: List<Link>
) : Parcelable
