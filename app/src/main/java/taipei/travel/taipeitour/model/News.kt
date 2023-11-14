package taipei.travel.taipeitour.model

import java.util.Date

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
)
