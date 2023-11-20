package taipei.travel.taipeitour.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Activity(
        @SerializedName("distric")
        val district: String,

        val address: String,

        @SerializedName("nlat")
        val latitudeNorth: String,

        @SerializedName("elong")
        val longitudeEast: String,

        val organizer: String,

        @SerializedName("co_rganizer")
        val coOrganizer: String,

        val contact: String,

        val tel: String,

        val fax: String,

        val ticket: String,

        val traffic: String,

        val parking: String,

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
