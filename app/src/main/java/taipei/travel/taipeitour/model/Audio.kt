package taipei.travel.taipeitour.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Audio(
        val id: Long,
        val title: String,
        val summary: String?,
        val url: String,
        @SerializedName("file_ext") val ext: String?,
        val modified: Date
)
