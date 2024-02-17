package taipei.travel.taipeitour.model.api.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Tour(
        val id: Int,

        @SerializedName("seasonsraw")
        val rawSeasons: String,

        @SerializedName("monthsraw")
        val rawMonths: String,

        val seasons: List<String>,

        val months: List<String>,

        val days: Int,

        val title: String,

        val author: String,

        val description: String,

        val consume: String,

        val remark: String,

        val note: String,

        val url: String,

        @SerializedName("category")
        val categories: List<Category>?,

        @SerializedName("transport")
        val transports: List<Category>?,

        val users: List<Category>?,

        val modified: Date,

        val files: List<File>
) : Parcelable
