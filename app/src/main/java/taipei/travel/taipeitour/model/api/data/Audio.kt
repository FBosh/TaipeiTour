package taipei.travel.taipeitour.model.api.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Audio(
        val id: Long,
        val title: String,
        val summary: String?,
        val url: String,
        @SerializedName("file_ext") val ext: String?,
        val modified: Date
) : Parcelable
