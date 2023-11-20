package taipei.travel.taipeitour.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Attraction(
        val id: Int,

        val name: String,

        @SerializedName("name_zh")
        val nameZH: String?,

        @SerializedName("open_status")
        val openStatus: Int,

        val introduction: String,

        @SerializedName("open_time")
        val openTime: String,

        val zipcode: String,

        @SerializedName("distric")
        val district: String,

        val address: String,

        val tel: String,

        val fax: String,

        val email: String,

        val months: String,

        @SerializedName("nlat")
        val latitudeNorth: Double,

        @SerializedName("elong")
        val longitudeEast: Double,

        @SerializedName("official_site")
        val officialSite: String,

        val facebook: String,

        val ticket: String,

        @SerializedName("remind")
        val reminders: String,

        @SerializedName("staytime")
        val stayTime: String,

        val modified: Date,

        val url: String,

        @SerializedName("category")
        val categories: List<Category>,

        @SerializedName("target")
        val targets: List<Category>,

        @SerializedName("service")
        val services: List<Category>,

        @SerializedName("friendly")
        val friendlyServices: List<Category>,

        val images: List<Image>,

        val files: List<File>,

        val links: List<Link>
) : Parcelable
