package taipei.travel.taipeitour.model.api.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Calendar(
        @SerializedName("distric")
        val district: String,

        val address: String,

        @SerializedName("nlat")
        val latitudeNorth: String,

        @SerializedName("elong")
        val longitudeEast: String,

        val tel: String,

        val fax: String,

        val ticket: String,

        val traffic: String,

        val parking: String,

        @SerializedName("is_major")
        val isMajor: Boolean,

        val id: Int,

        val title: String,

        val description: String,

        val begin: Date,

        val end: Date,

        val posted: Date,

        val modified: Date,

        val url: String,

        val files: List<File>,

        val links: List<Link>
) : Parcelable
